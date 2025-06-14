package com.future_ride.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.future_ride.entities.Rider;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {

}
