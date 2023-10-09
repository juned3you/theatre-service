package com.xyz.theatreService.exception;

/**
 * Custom exception for Seat already reserved.
 */
public final class SeatReservedException extends Exception {
    public SeatReservedException(String errorMessage) {
        super(errorMessage);
    }
}
