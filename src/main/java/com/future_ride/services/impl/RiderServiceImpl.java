package com.future_ride.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.future_ride.dto.RiderCreateRequest;
import com.future_ride.entities.Rider;
import com.future_ride.exceptions.RiderNotFoundException;
import com.future_ride.repositories.RiderRepository;
import com.future_ride.services.RiderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {

    private final RiderRepository riderRepository;
    private final ModelMapper mapper;

    @Override
    public Rider createRider(RiderCreateRequest request) {
        Rider newRider = mapper.map(request, Rider.class);
        return riderRepository.save(newRider);
    }

    @Override
    public Rider updateRider(Long riderId, RiderCreateRequest request) {
        Rider rider = getRiderById(riderId);

        if (request.getName() != null) {
            rider.setName(request.getName());
        }
        if (request.getPhoneNumber() != null) {
            rider.setPhone(request.getPhoneNumber());
        }
        return riderRepository.save(rider);
    }

    @Override
    public Rider getRiderById(Long riderId) {
        return riderRepository.findById(riderId)
                .orElseThrow(() -> new RiderNotFoundException("Rider not found with riderId: " + riderId));
    }

    @Override
    public List<Rider> getAll() {
        List<Rider> optionalRider = riderRepository.findAll();
        return optionalRider;

    }

}
