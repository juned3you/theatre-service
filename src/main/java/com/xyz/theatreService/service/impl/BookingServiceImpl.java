package com.xyz.theatreService.service.impl;

import com.xyz.theatreService.dto.BookingDto;
import com.xyz.theatreService.entity.*;
import com.xyz.theatreService.enums.BookingStatus;
import com.xyz.theatreService.exception.BookingException;
import com.xyz.theatreService.exception.InvalidDataException;
import com.xyz.theatreService.exception.SeatReservedException;
import com.xyz.theatreService.pojo.BookingCriteria;
import com.xyz.theatreService.repository.*;
import com.xyz.theatreService.service.BookingService;
import com.xyz.theatreService.utils.DateUtils;
import com.xyz.theatreService.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Locale;
import java.util.Optional;

/**
 * Bookings ticket service.
 */
@Service
public class BookingServiceImpl implements BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);
    private static final String THEATRE_ERROR_KEY = "api.error.theatre.not.found";
    private static final String SCREEN_ERROR_KEY = "api.error.screen.not.found";
    private static final String SEAT_ERROR_KEY = "api.error.seat.not.found";
    private static final String TIMINGS_ERROR_KEY = "api.error.timings.not.found";
    private static final String THEATRE_SCREEN_NOT_MATCHED_ERROR_KEY = "api.error.screen.theatre.not.found";
    private static final String SHOW_NOT_PRESENT = "api.error.show.not.found";
    private static final String SEAT_ALREADY_RESERVED_ERROR_KEY = "api.error.seat.reserved";
    private static final String BOOKING_SUCCESS = "api.message.booking.success";
    private static final Long TEMP_USER_ID = 1L;

    private final TheatreRepository theatreRepository;

    private final ScreenRepository screenRepository;

    private final SeatRepository seatRepository;

    private final TimingsRepository timingsRepository;

    private final ShowTimeRepository showTimeRepository;

    private final BookingRepository bookingRepository;

    private final MessageSource messageSource;

    public BookingServiceImpl(@Autowired TheatreRepository theatreRepository, @Autowired ScreenRepository screenRepository,
                              @Autowired SeatRepository seatRepository, @Autowired TimingsRepository timingsRepository,
                              @Autowired ShowTimeRepository showTimeRepository, @Autowired BookingRepository bookingRepository,
                              @Autowired MessageSource messageSource) {
        this.theatreRepository = theatreRepository;
        this.screenRepository = screenRepository;
        this.seatRepository = seatRepository;
        this.timingsRepository = timingsRepository;
        this.showTimeRepository = showTimeRepository;
        this.bookingRepository = bookingRepository;
        this.messageSource = messageSource;
    }

    /**
     * Method for Booking ticket.
     *
     * @param bookingCriteria
     * @return BookingDto
     * @throws SeatReservedException
     * @throws InvalidDataException
     */
    @Override
    public BookingDto bookTicket(BookingCriteria bookingCriteria) throws SeatReservedException, InvalidDataException,
            BookingException {
        logger.info("bookTicket called: {}", bookingCriteria);

        //Validate inputs
        validateInputs(bookingCriteria);


        //Validating seat reserve in cache by other users.
        if (isSeatAlreadySelected()) {
            throw new SeatReservedException(messageSource.getMessage(SEAT_ALREADY_RESERVED_ERROR_KEY, null,
                    Locale.getDefault()));
        }

        try {
            //Save Booking
            return saveBooking(bookingCriteria);
        } catch (Exception exception) {
            throw new BookingException(exception.getMessage(), exception);
        }
    }

    /**
     * This methoed check if other user already selected the seat stored in cache.
     * If yes, we don't allow this user to reserve this seat.
     */
    private boolean isSeatAlreadySelected() {
        return false;
    }

    private void validateInputs(BookingCriteria bookingCriteria) throws InvalidDataException, SeatReservedException {

        //Theatre should be valid.
        if (!theatreRepository.existsById(bookingCriteria.getTheatreId())) {
            throw getInvalidDataException(THEATRE_ERROR_KEY);
        }

        //Screen should be valid.
        final Optional<Screen> optionalScreen = screenRepository.findById(bookingCriteria.getScreenId());
        if (optionalScreen.isEmpty()) {
            throw getInvalidDataException(SCREEN_ERROR_KEY);
        }

        // Seat should be present.
        final Optional<Seat> optionalSeat = seatRepository.findById(bookingCriteria.getSeatId());
        if (optionalSeat.isEmpty()) {
            throw getInvalidDataException(SEAT_ERROR_KEY);
        }

        //Provided timings should be present.
        final Optional<Timings> optionalTimings = timingsRepository.findById(bookingCriteria.getTimingsId());
        if (optionalTimings.isEmpty()) {
            throw getInvalidDataException(TIMINGS_ERROR_KEY);
        }

        //Screen should be a part of theatre.
        final Screen screen = optionalScreen.get();
        if (!screen.getTheatre().getId().equals(bookingCriteria.getTheatreId())) {
            throw getInvalidDataException(THEATRE_SCREEN_NOT_MATCHED_ERROR_KEY);
        }

        //Searching show time for the provided data.
        Optional<ShowTime> optionalShowTime = showTimeRepository.findByScreenAndTimingsAndShowDate(screen,
                optionalTimings.get(),
                DateUtils.formatDate(bookingCriteria.getShowDate()));

        if (optionalShowTime.isEmpty()) {
            throw getInvalidDataException(SHOW_NOT_PRESENT);
        }

        final ShowTime showTime = optionalShowTime.get();
        Optional<Booking> optionalBooking = bookingRepository.findByShowTimeAndSeat(showTime, optionalSeat.get());

        //Checking existing booking before assigning seat.
        if (optionalBooking.isPresent()) {
            throw new SeatReservedException(messageSource.getMessage(SEAT_ALREADY_RESERVED_ERROR_KEY, null,
                    Locale.getDefault()));
        }
    }

    private InvalidDataException getInvalidDataException(String key) {
        return new InvalidDataException(messageSource.getMessage(key, null, Locale.getDefault()));
    }

    /**
     * Create Booking with Optimistic looking.
     *
     * @param bookingCriteria
     * @return BookingDto
     * @throws InvalidDataException
     * @throws SeatReservedException
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    private BookingDto saveBooking(BookingCriteria bookingCriteria) throws InvalidDataException, SeatReservedException {
        final Screen screen = screenRepository.findById(bookingCriteria.getScreenId()).get();
        final Timings timings = timingsRepository.findById(bookingCriteria.getTimingsId()).get();

        //Searching show time for the provided data.
        final ShowTime showTime = showTimeRepository.findByScreenAndTimingsAndShowDate(screen, timings,
                DateUtils.formatDate(bookingCriteria.getShowDate())).get();

        final Seat seat = seatRepository.findById(bookingCriteria.getSeatId()).get();

        final Booking booking = new Booking();
        booking.setSeat(seat);
        booking.setStatus(BookingStatus.BOOKED);
        booking.setDateTime(new Date());
        booking.setShowTime(showTime);
        booking.setUserId(TEMP_USER_ID);
        Booking persistBooking = bookingRepository.saveAndFlush(booking);

        logger.info("Booking successfully done with id: {}", persistBooking.getId());
        return new BookingDto(persistBooking.getId(),
                messageSource.getMessage(BOOKING_SUCCESS, null, Locale.getDefault()),
                persistBooking.getStatus().name(),
                StringUtils.getShowTimeString(timings),
                screen.getName());
    }
}
