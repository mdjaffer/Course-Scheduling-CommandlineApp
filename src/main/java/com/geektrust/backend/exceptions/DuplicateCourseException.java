package com.geektrust.backend.exceptions;

public class DuplicateCourseException extends RuntimeException {
    
    public DuplicateCourseException() {
        super();
    }

    public DuplicateCourseException(String message) {
        super(message);
    }
}
