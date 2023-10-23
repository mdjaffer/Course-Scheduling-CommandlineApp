package com.geektrust.backend.exceptions;

public class InstructorAlreadyAssignedException extends RuntimeException {

    public InstructorAlreadyAssignedException() {
        super();
    }

    public InstructorAlreadyAssignedException(String message) {
        super(message);
    }
}
