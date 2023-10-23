package com.geektrust.backend.exceptions;

public class InvalidDateException extends RuntimeException {

    public InvalidDateException() {
        super();
    }

    public InvalidDateException(String message) {
        super(message);
    }
}
