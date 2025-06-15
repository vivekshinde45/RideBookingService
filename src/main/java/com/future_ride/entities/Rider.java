package com.future_ride.entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Rider extends User {
    public Rider(String name, String phone) {
        super(name, phone);
    }

    public Rider() {
    }
}
