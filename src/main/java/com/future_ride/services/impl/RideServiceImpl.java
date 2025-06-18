package com.future_ride.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.future_ride.dto.RideRequestDto;
import com.future_ride.entities.Driver;
import com.future_ride.entities.Ride;
import com.future_ride.exceptions.RideNotFoundException;
import com.future_ride.repositories.RideRepository;
import com.future_ride.services.DriverService;
import com.future_ride.services.RideService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {
    private final RideRepository rideRepository;
    private final DriverService driverService;

    @Override
    public Ride createRide(RideRequestDto request) {
        Ride ride = new Ride(request.getSource(), request.getDestination(), request.getExpectedStartTime(),
                request.getExpectedEndTime());
        return rideRepository.save(ride);
    }

    @Override
    public Ride getRideById(Long rideId) {
        return rideRepository.findById(rideId)
                .orElseThrow(
                        () -> new RideNotFoundException("Ride not found with rideId" + rideId));
    }

    @Override
    public Ride assignRideToDriver(Long rideId, Long driverId) throws Exception {
        Ride ride = getRideById(rideId);
        Driver driver = driverService.getDriverById(driverId);
        ride.assignRide(driver);
        return rideRepository.save(ride);
    }

    @Override
    public Ride startRide(Long rideId) throws Exception {
        Ride ride = getRideById(rideId);
        ride.startRide();
        return rideRepository.save(ride);
    }

    @Override
    public Ride completeRide(Long rideId) throws Exception {
        Ride ride = getRideById(rideId);
        ride.complete();
        return rideRepository.save(ride);
    }

    @Override
    public Ride cancelRide(Long rideId) throws Exception {
        Ride ride = getRideById(rideId);
        ride.cancel();
        return rideRepository.save(ride);
    }

}
