package com.future_ride.services;

import com.future_ride.dto.DriverCreateRequest;
import com.future_ride.entities.Driver;

import java.util.List;

public interface DriverService {
    Driver createDriver(DriverCreateRequest request);

    Driver updateDriver(Long driverId, DriverCreateRequest request);

    Driver updateRating(Long driverId, Double rating);

    Driver getDriverById(Long driverId);

    List<Driver> getAll();
}