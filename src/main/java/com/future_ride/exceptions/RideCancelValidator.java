package com.future_ride.exceptions;

public class RideCancelValidator extends RuntimeException {
    public RideCancelValidator(){

    }

    public RideCancelValidator(String message){
        super(message);
    }
}
