package com.xyz.theatreService.exception;

/**
 * Custom exception for Search Theatres for the choosen date.
 */
public final class SearchTheatreException extends Exception {
    public SearchTheatreException(String errorMessage) {
        super(errorMessage);
    }

    public SearchTheatreException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }
}
