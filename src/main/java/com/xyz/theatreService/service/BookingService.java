package com.xyz.theatreService.service;

import com.xyz.theatreService.dto.BookingDto;
import com.xyz.theatreService.exception.BookingException;
import com.xyz.theatreService.exception.InvalidDataException;
import com.xyz.theatreService.exception.SeatReservedException;
import com.xyz.theatreService.pojo.BookingCriteria;

public interface BookingService {
    BookingDto bookTicket(BookingCriteria bookingCriteria) throws SeatReservedException, InvalidDataException,
            BookingException;
}
