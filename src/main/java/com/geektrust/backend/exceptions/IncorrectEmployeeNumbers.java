package com.geektrust.backend.exceptions;

public class IncorrectEmployeeNumbers extends RuntimeException {

    public IncorrectEmployeeNumbers() {
        super();
    }

    public IncorrectEmployeeNumbers(String message) {
        super(message);
    }
}
