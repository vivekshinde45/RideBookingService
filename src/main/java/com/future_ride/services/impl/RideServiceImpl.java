package com.future_ride.services.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.future_ride.dto.RideRequestDto;
import com.future_ride.entities.Driver;
import com.future_ride.entities.Ride;
import com.future_ride.entities.Rider;
import com.future_ride.exceptions.PastRidesNotAllowedException;
import com.future_ride.exceptions.RideNotFoundException;
import com.future_ride.repositories.RideRepository;
import com.future_ride.services.DriverService;
import com.future_ride.services.RideService;
import com.future_ride.services.RiderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {
    private final RideRepository rideRepository;
    private final DriverService driverService;
    private final RiderService riderService;

    @Override
    public Ride createRide(RideRequestDto request) {
        // A Ride can be scheduled for immediate or future times, but not for past
        // times.
        if (request.getExpectedStartTime().isBefore(LocalDateTime.now())) {
            throw new PastRidesNotAllowedException("Ride cannot be scheduled in the past");
        }
        Rider rider = riderService.getRiderById(request.getRiderId());
        Ride ride = new Ride(request.getSource(), request.getDestination(), request.getExpectedStartTime(),
                request.getExpectedEndTime());
        ride.setRider(rider);
        return rideRepository.save(ride);
    }

    @Override
    public Ride getRideById(Long rideId) {
        return rideRepository.findById(rideId)
                .orElseThrow(
                        () -> new RideNotFoundException("Ride not found with rideId: " + rideId));
    }

    @Override
    public Ride assignRideToDriver(Long rideId, Long driverId) {
        Ride ride = getRideById(rideId);
        Driver driver = driverService.getDriverById(driverId);
        ride.assignRide(driver);
        return rideRepository.save(ride);
    }

    @Override
    public Ride startRide(Long rideId) {
        Ride ride = getRideById(rideId);
        ride.startRide();
        return rideRepository.save(ride);
    }

    @Override
    public Ride completeRide(Long rideId) {
        Ride ride = getRideById(rideId);
        ride.complete();
        return rideRepository.save(ride);
    }

    @Override
    public Ride cancelRide(Long rideId) {
        Ride ride = getRideById(rideId);
        ride.cancel();
        return rideRepository.save(ride);
    }

}
