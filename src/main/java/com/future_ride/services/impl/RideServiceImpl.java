package com.future_ride.services.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.future_ride.dto.RideRequestDto;
import com.future_ride.entities.Driver;
import com.future_ride.entities.Ride;
import com.future_ride.entities.RideStatus;
import com.future_ride.entities.Rider;
import com.future_ride.exceptions.InvalidRideStatusException;
import com.future_ride.exceptions.PastRidesNotAllowedException;
import com.future_ride.exceptions.RideCancelValidator;
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
        RideStatus status = ride.getStatus();
        if (!status.equals(RideStatus.WAITING)) {
            throw new InvalidRideStatusException("Invalid Ride Status Expected " + RideStatus.WAITING);
        }
        Driver driver = driverService.getDriverById(driverId);
        ride.assignRide(driver);
        return rideRepository.save(ride);
    }

    @Override
    public Ride startRide(Long rideId) {
        Ride ride = getRideById(rideId);
        RideStatus status = ride.getStatus();
        if (!status.equals(RideStatus.ASSIGNED)) {
            throw new InvalidRideStatusException("Invalid Ride Status Expected " + RideStatus.ASSIGNED);
        }
        ride.startRide();
        return rideRepository.save(ride);
    }

    @Override
    public Ride completeRide(Long rideId) {
        Ride ride = getRideById(rideId);
        RideStatus status = ride.getStatus();
        if (!status.equals(RideStatus.IN_PROGRESS)) {
            throw new InvalidRideStatusException("Invalid Ride Status Expected " + RideStatus.IN_PROGRESS);
        }
        ride.complete();
        return rideRepository.save(ride);
    }

    @Override
    public Ride cancelRide(Long rideId) {
        Ride ride = getRideById(rideId);
        // Check if it's within 1 hour of expected start time (for both riders and
        // drivers)
        // WAITING STATUS ASSIGNED 1 HOUR BEFORE
        if (!ride.getStatus().equals(RideStatus.WAITING)) {
            if (ride.getStatus().equals(RideStatus.ASSIGNED)) {
                LocalDateTime expectedCancellationTime = ride.getExpectedStartTime().minusHours(1);
                if (!LocalDateTime.now().isBefore(expectedCancellationTime)) {
                    throw new RideCancelValidator("Ride Cannot Be Cancelled Within 1 Hour Of Start Time");
                }
            } else {
                throw new InvalidRideStatusException("Ride Cannot Cancelled With Ride Status " + ride.getStatus());
            }
        }

        ride.cancel();
        return rideRepository.save(ride);
    }

}
