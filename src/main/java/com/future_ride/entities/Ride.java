package com.future_ride.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(org.springframework.data.jpa.domain.support.AuditingEntityListener.class)
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
        this.expectedStartTime = expectedStartTime;
        this.expectedEndTime = expectedEndTime;
        this.status = RideStatus.WAITING;
    }

    @ManyToOne // many rides can be assigned to one rider
    private Rider rider;

    @OneToOne
    private Driver driver;

    @OneToOne
    private Rating rating;

    public void complete() {
        this.status = RideStatus.COMPLETED;
        this.endTime = LocalDateTime.now();
    }

    public void cancel() {
        this.status = RideStatus.CANCELLED;
        this.driver = null;
    }

    public void assignRide(Driver driver) {
        this.driver = driver;
        this.status = RideStatus.ASSIGNED;
    }

    public void startRide() {
        this.status = RideStatus.IN_PROGRESS;
        this.startTime = LocalDateTime.now();
    }
}
