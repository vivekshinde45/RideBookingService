package com.future_ride.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.future_ride.exceptions.RideNotFoundException;
import com.future_ride.exceptions.RiderNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RiderNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> RiderNotFoundExceptionHandler(RiderNotFoundException exception) {
        ApiError error = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return buildErrorResponseEntity(error);
    }

    @ExceptionHandler(RideNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> RideNotFoundExceptionHandler(RideNotFoundException exception) {
        ApiError error = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return buildErrorResponseEntity(error);
    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError error) {
        return new ResponseEntity<>(new ApiResponse<>(error), error.getStatus());
    }
}
