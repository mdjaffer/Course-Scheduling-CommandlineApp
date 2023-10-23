package com.geektrust.backend.exceptions;

public class RegistrationNotAvailableException extends RuntimeException{
    
    public RegistrationNotAvailableException() {
        super();
    }

    public RegistrationNotAvailableException(String message) {
        super(message);
    }
}
