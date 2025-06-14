package com.future_ride.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.future_ride.entities.Ride;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {

}