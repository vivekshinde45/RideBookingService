package com.future_ride.services;

import com.future_ride.dto.RideRequestDto;
import com.future_ride.entities.Ride;

public interface RideService {
    Ride createRide(RideRequestDto request);

    Ride getRideById(Long rideId);

    Ride assignRideToDriver(Long rideId, Long driverId);

    Ride startRide(Long rideId); // ride is In progress

    Ride completeRide(Long rideId);

    Ride cancelRide(Long rideId);

}
