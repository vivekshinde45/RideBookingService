package com.future_ride.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.future_ride.dto.RideRequestDto;
import com.future_ride.entities.Driver;
import com.future_ride.entities.Ride;
import com.future_ride.repositories.RideRepository;
import com.future_ride.services.DriverService;
import com.future_ride.services.RideService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {
    private final RideRepository rideRepository;
    private final ModelMapper modelMapper;
    private final DriverService driverService;

    @Override
    public Ride createRide(RideRequestDto request) {

        Ride ride = modelMapper.map(request, Ride.class);
        return rideRepository.save(ride);

    }

    @Override
    public Ride getRideById(Long rideId) throws Exception {

        // Optional<Ride> optionalRide = rideRepository.findById(rideId);
        // if (optionalRide.isPresent()) {
        // return optionalRide.get();
        // } else {
        // throw new Exception("Ride not found with rideId" + rideId);
        // }
        return rideRepository.findById(rideId).orElseThrow(() -> new Exception("Ride not found with rideId" + rideId));

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
