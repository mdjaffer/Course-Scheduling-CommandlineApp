package com.geektrust.backend.exceptions;

public class CourseFullException extends RuntimeException {
    
    public CourseFullException() {
        super();
    }

    public CourseFullException(String message) {
        super(message);
    }
}
