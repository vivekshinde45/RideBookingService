package com.future_ride.services;

import com.future_ride.dto.RideRequestDto;
import com.future_ride.entities.Ride;

public interface RideService {
    Ride createRide(RideRequestDto request);

    Ride getRideById(Long rideId) throws Exception;

    Ride assignRideToDriver(Long rideId,Long driverId) throws Exception;

    Ride startRide(Long rideId) throws Exception; //In progress

    Ride completeRide(Long rideId) throws Exception;

    Ride cancelRide(Long rideId) throws Exception;



    
}
