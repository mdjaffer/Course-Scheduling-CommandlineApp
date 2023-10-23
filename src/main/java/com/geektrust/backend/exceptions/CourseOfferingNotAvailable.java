package com.geektrust.backend.exceptions;

public class CourseOfferingNotAvailable extends RuntimeException {
    
    public CourseOfferingNotAvailable() {
        super();
    }

    public CourseOfferingNotAvailable(String message) {
        super(message);
    }
}
