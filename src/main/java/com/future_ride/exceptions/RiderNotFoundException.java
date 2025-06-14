package com.future_ride.exceptions;

public class RiderNotFoundException extends RuntimeException {
    public RiderNotFoundException() {
    }

    public RiderNotFoundException(String message) {
        super(message);
    }
}