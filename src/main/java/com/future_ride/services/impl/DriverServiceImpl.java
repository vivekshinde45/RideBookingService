package com.future_ride.services.impl;

import com.future_ride.dto.DriverCreateRequest;
import com.future_ride.entities.Driver;
import com.future_ride.exceptions.DriverNotFoundException;
import com.future_ride.repositories.DriverRepository;

import com.future_ride.services.DriverService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;

    @Override
    public Driver createDriver(DriverCreateRequest request) {
        Driver driver = new Driver(request.getName(), request.getPhoneNumber(), request.getVehicleNumber());
        return driverRepository.save(driver);
    }

    @Override
    public Driver updateDriver(Long driverId, DriverCreateRequest request) {
        Driver driver = getDriverById(driverId);
        if (request.getName() != null) {
            driver.setName(request.getName());
        }
        if (request.getPhoneNumber() != null) {
            driver.setPhone(request.getPhoneNumber());
        }
        if (request.getVehicleNumber() != null) {
            driver.setVehicleNumber(request.getVehicleNumber());
        }

        return driverRepository.save(driver);
    }

    @Override
    public Driver getDriverById(Long driverId) {
        return driverRepository.findById(driverId)
                .orElseThrow(() -> new DriverNotFoundException("Driver not found with driverId: " + driverId));
    }

    @Override
    public List<Driver> getAll() {
        return driverRepository.findAll();
    }

    @Override
    public Driver updateRating(Long driverId, Double rating) {
        // Driver driver = getDriverById(driverId);
        return null;
    }

}
