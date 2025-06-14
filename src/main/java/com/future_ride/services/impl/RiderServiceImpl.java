package com.future_ride.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.future_ride.dto.RiderCreateRequest;
import com.future_ride.entities.Rider;
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
    public Rider updateRider(Long riderId, RiderCreateRequest request) throws Exception {
        Optional<Rider> optionalRider = riderRepository.findById(riderId);
        if (optionalRider.isPresent()) {
            Rider rider = optionalRider.get();
            if (request.getName() != null) {
                rider.setName(request.getName());
            }
            if (request.getPhoneNumber() != null) {
                rider.setPhone(request.getPhoneNumber());
            }
            return riderRepository.save(rider);
        } else {
            throw new Exception("Rider Not Found with ID: " + riderId);
        }
    }

    @Override
    public Rider getRiderById(Long riderId) throws Exception{
        Optional<Rider> optionalRider = riderRepository.findById(riderId);
        if (optionalRider.isPresent()) {
            Rider rider = optionalRider.get();
            return rider;
        } else {
            throw new Exception("Rider Not Found with ID: " + riderId);
        }
    }

    @Override
    public List<Rider> getAll() {
        List<Rider> optionalRider = riderRepository.findAll();
        return optionalRider;
        
    }

}
