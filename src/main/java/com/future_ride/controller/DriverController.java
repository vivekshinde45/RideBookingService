package com.future_ride.controller;

import com.future_ride.dto.DriverCreateRequest;
import com.future_ride.entities.Driver;
import com.future_ride.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driver")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @PostMapping("/create")
    public ResponseEntity<Driver> createDriver(@RequestBody DriverCreateRequest request) {
        Driver driver = driverService.createDriver(request);
        return new ResponseEntity<>(driver, HttpStatus.CREATED);
    }

    @PostMapping("/{driverId}/update")
    public ResponseEntity<Driver> updateDriver(@PathVariable Long driverId, @RequestBody DriverCreateRequest request) {
        Driver updatedDriver = driverService.updateDriver(driverId, request);
        return new ResponseEntity<>(updatedDriver, HttpStatus.OK);
    }

    @GetMapping("/{driverId}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long driverId) {
        Driver driver = driverService.getDriverById(driverId);
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Driver>> getAll() {
        List<Driver> driver = driverService.getAll();
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }
}
