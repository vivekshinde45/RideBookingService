package com.future_ride.advice;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private LocalDateTime timeStamp;
    private T data;
    private String error;
}
