package com.future_ride.exceptions;

public class RatingNotFoundException extends RuntimeException {
    public RatingNotFoundException() {
    }

    public RatingNotFoundException(String message) {
        super(message);
    }
}
