package com.xyz.theatreService.exception;

/**
 * Custom exception for Seat already reserved.
 */
public final class BookingException extends Exception {
    public BookingException(String errorMessage) {
        super(errorMessage);
    }

    public BookingException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }
}
