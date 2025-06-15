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
    public ResponseEntity<Driver> createDriver(@RequestBody DriverCreateRequest request){
        Driver driver = driverService.createDriver(request);
        return new ResponseEntity<>(driver, HttpStatus.CREATED);
    }

    @PostMapping("/update/{driverId}")
    public ResponseEntity<Driver> updateDriver(@PathVariable Long driverId,@RequestBody DriverCreateRequest request){
        try {
            Driver updatedDriver = driverService.updateDriver(driverId,request);
            return new ResponseEntity<>(updatedDriver,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{driverId}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long driverId){
         try {
             Driver driver = driverService.getDriverById(driverId);
             return new ResponseEntity<>(driver,HttpStatus.OK);
         }catch(Exception e){
             return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
         }
    }

    @GetMapping
    public ResponseEntity<List<Driver>> getAll(){
        List<Driver> driver =  driverService.getAll();
        return new ResponseEntity<>(driver,HttpStatus.OK);
    }
}
