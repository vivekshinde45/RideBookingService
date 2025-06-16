package com.future_ride.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.future_ride.dto.RiderCreateRequest;
import com.future_ride.entities.Rider;
import com.future_ride.services.RiderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rider")
@RequiredArgsConstructor
public class RiderController {

    private final RiderService rideService;

    @PostMapping("/create")
    public ResponseEntity<Rider> createRider(@RequestBody RiderCreateRequest request) {
        System.out.println("Hello");
        Rider savedRider = rideService.createRider(request);
        return new ResponseEntity<>(savedRider, HttpStatus.CREATED);
    }

    @PostMapping("/{riderId}/update")
    public ResponseEntity<Rider> updateRider(@PathVariable Long riderId, @RequestBody RiderCreateRequest request) {
        try {
            Rider rider = rideService.updateRider(riderId, request);
            return new ResponseEntity<>(rider, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{riderId}")
    public ResponseEntity<Rider> getRiderById(@PathVariable Long riderId) {
        try {
            Rider rider = rideService.getRiderById(riderId);
            return new ResponseEntity<>(rider, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Rider>> getAll() {
        try {
            List<Rider> rider = rideService.getAll();
            return new ResponseEntity<>(rider, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
