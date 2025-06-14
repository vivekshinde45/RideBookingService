package com.future_ride.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String soruce;
    private String destination;
    private LocalDateTime expectedStartTime;
    private LocalDateTime expectedEndTime;
    private RideStatus status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime lastUpdatedAt;

    public Ride(String src, String dst, LocalDateTime expectedStartTime, LocalDateTime expectedEndTime) {
        this.soruce = src;
        this.destination = dst;
        this.expectedStartTime = expectedEndTime;
        this.expectedEndTime = expectedEndTime;
        this.status = RideStatus.WAITING;
    }

    @OneToOne
    private Rider rider;

    @OneToOne
    private Driver driver;

    @OneToOne
    private Rating rating;

    public void complete() {
        this.status = RideStatus.COMPLETED;
    }

    public void cancel() {
        this.status = RideStatus.CANCELLED;
        this.driver = null;
    }

    public void assignRide(Driver driver) {
        this.driver = driver;
        this.status = RideStatus.ASSIGNED;
    }
}
