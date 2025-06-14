package com.future_ride.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RideRequestDto {
    private String source;
    private String destination;
    private LocalDateTime expectedStartTime;
    private LocalDateTime expectedEndTime;
    private Long riderId;
}
