package com.xyz.theatreService.controller;

import com.xyz.theatreService.dto.BookingDto;
import com.xyz.theatreService.exception.BookingException;
import com.xyz.theatreService.exception.InvalidDataException;
import com.xyz.theatreService.exception.SeatReservedException;
import com.xyz.theatreService.pojo.BookingCriteria;
import com.xyz.theatreService.service.BookingService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
@Validated
public class BookingController {

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    private final BookingService bookingService;

    public BookingController(@Autowired BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public BookingDto bookingTicket(@Valid @RequestBody BookingCriteria bookingCriteria) throws InvalidDataException,
            SeatReservedException, BookingException {
        return bookingService.bookTicket(bookingCriteria);
    }
}
