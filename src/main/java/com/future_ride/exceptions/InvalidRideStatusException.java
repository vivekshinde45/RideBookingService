package com.future_ride.exceptions;

public class InvalidRideStatusException extends RuntimeException {
    public InvalidRideStatusException() {
    }

    public InvalidRideStatusException(String message) {
        super(message);
    }
    
}
