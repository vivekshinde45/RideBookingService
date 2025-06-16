package com.future_ride.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.future_ride.dto.RideRequestDto;
import com.future_ride.entities.Ride;
import com.future_ride.helper.Utils;
import com.future_ride.services.RideService;

@RestController
@RequestMapping("/api/ride")
public class RideController {
    @Autowired
    private RideService rideService;

    @PostMapping("/create")
    public ResponseEntity<Ride> createRide(@RequestBody RideRequestDto request) {
        try {
            Ride ride = rideService.createRide(request);
            return Utils.mapToResponseEntity(ride, HttpStatus.CREATED);
        } catch (Exception e) {
            return Utils.mapToResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{rId}")
    public ResponseEntity<Ride> getRideById(@PathVariable(name = "rId") Long rideId) {
        try {
            Ride ride = rideService.getRideById(rideId);
            return Utils.mapToResponseEntity(ride, HttpStatus.OK);
        } catch (Exception e) {
            return Utils.mapToResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{rideId}/assign/{driverId}")
    public ResponseEntity<Ride> assignRideToDriver(@PathVariable Long rideId, @PathVariable Long driverId) {
        try {
            Ride ride = rideService.assignRideToDriver(rideId, driverId);
            return Utils.mapToResponseEntity(ride, HttpStatus.OK);
        } catch (Exception e) {
            return Utils.mapToResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{rideId}/start")
    public ResponseEntity<Ride> startRide(@PathVariable Long rideId) {
        try {
            Ride ride = rideService.startRide(rideId);
            return Utils.mapToResponseEntity(ride, HttpStatus.OK);
        } catch (Exception e) {
            return Utils.mapToResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{rideId}/cancel")
    public ResponseEntity<Ride> cancelRide(@PathVariable Long rideId) {
        try {
            Ride ride = rideService.cancelRide(rideId);
            return Utils.mapToResponseEntity(ride, HttpStatus.OK);
        } catch (Exception e) {
            return Utils.mapToResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{rideId}/complete")
    public ResponseEntity<Ride> completeRide(@PathVariable Long rideId) {
        try {
            Ride ride = rideService.completeRide(rideId);
            return Utils.mapToResponseEntity(ride, HttpStatus.OK);
        } catch (Exception e) {
            return Utils.mapToResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }

}
