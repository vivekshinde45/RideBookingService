package com.future_ride.services;

import com.future_ride.dto.DriverCreateRequest;
import com.future_ride.entities.Driver;

import java.util.List;

public interface DriverService {
    Driver createDriver(DriverCreateRequest request);

    Driver updateDriver(Long driverId, DriverCreateRequest request) throws Exception;

    Driver getDriverById(Long driverId) throws Exception;

    List<Driver> getAll();
}