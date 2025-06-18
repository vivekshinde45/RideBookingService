package com.future_ride.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Utils {
    public static <T> ResponseEntity<T> mapToResponseEntity(T data, HttpStatus httpStatus){
        return new ResponseEntity<>(data, httpStatus);
    }
}
