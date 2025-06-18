package com.future_ride.exceptions;

public class RideNotFoundException extends RuntimeException {
    public RideNotFoundException() {

    }

    public RideNotFoundException(String message) {
        super(message);
    }
}
