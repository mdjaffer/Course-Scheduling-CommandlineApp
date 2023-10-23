package com.geektrust.backend.exceptions;

public class CourseAlreadyAssignedException extends RuntimeException {
    
    public CourseAlreadyAssignedException() {
        super();
    }

    public CourseAlreadyAssignedException(String message) {
        super(message);
    }
}
