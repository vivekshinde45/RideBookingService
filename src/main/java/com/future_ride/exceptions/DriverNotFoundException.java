package com.future_ride.exceptions;

public class DriverNotFoundException extends RuntimeException {
    public DriverNotFoundException() {
    }

    public DriverNotFoundException(String message) {
        super(message);
    }
}
