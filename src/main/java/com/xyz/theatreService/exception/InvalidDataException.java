package com.xyz.theatreService.exception;

/**
 * Custom exception Invalid Data.
 */
public final class InvalidDataException extends Exception {

    public InvalidDataException(String errorMessage) {
        super(errorMessage);
    }
}
