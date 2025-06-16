package com.future_ride.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Driver extends User {
    private double avgRating;
    private int totalAssignedRides;
    private int totalCancelledRides;

    @OneToMany
    private List<Rating> ratings;

    @Column(nullable = false)
    @Pattern(regexp = "^[A-Z]{2}[0-9]{2}[A-Z]{0,2}[0-9]{4}[A-Z]{0,2}$", message = "Vehicle number must follow Indian format (e.g., KA01AB1234)")
    private String vehicleNumber;

    public Driver(String name, String phone, String email, String vehicleNumber) {
        super(name, phone);
        this.vehicleNumber = vehicleNumber;
        // initially avg rating for every driver is 3.0
        this.avgRating = 3.0;
        this.totalAssignedRides = 0;
        this.totalCancelledRides = 0;
    }
}
