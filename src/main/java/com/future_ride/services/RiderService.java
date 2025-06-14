package com.future_ride.services;

import java.util.List;

import com.future_ride.dto.RiderCreateRequest;
import com.future_ride.entities.Rider;

public interface RiderService {
    Rider createRider(RiderCreateRequest request);

    Rider updateRider(Long riderId, RiderCreateRequest request) throws Exception;

    Rider getRiderById(Long riderId) throws Exception;

    List<Rider> getAll();
}
