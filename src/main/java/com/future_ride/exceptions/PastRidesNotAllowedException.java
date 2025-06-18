package com.future_ride.exceptions;

public class PastRidesNotAllowedException extends RuntimeException {
    public PastRidesNotAllowedException() {
    }

    public PastRidesNotAllowedException(String message) {
        super(message);
    }
}
