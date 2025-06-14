package com.future_ride.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.future_ride.entities.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

}
