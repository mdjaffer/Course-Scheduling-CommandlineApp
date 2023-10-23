package com.geektrust.backend.exceptions;

public class WhiteSpacesInFieldsException extends RuntimeException {
    
    public WhiteSpacesInFieldsException() {
        super();
    }

    public WhiteSpacesInFieldsException(String message) {
        super(message);
    }
}
