package com.future_ride.services.impl;

import com.future_ride.dto.DriverCreateRequest;
import com.future_ride.entities.Driver;
import com.future_ride.repositories.DriverRepository;

import com.future_ride.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final ModelMapper mapper;
    @Override
    public Driver createDriver(DriverCreateRequest request) {
        Driver newDriver = mapper.map(request,Driver.class);
        return driverRepository.save(newDriver);

    }

    @Override
    public Driver updateDriver(Long driverId, DriverCreateRequest request) throws Exception {
        Driver driver = getDriverById(driverId);
        if(request.getName()!=null){
            driver.setName(request.getName());
        }
        if(request.getPhoneNumber()!=null){
            driver.setPhone(request.getPhoneNumber());
        }
        if(request.getVehicleNumber() != null){
            driver.setVehicleNumber(request.getVehicleNumber());
        }

        return driverRepository.save(driver);
    }


    @Override
    public Driver getDriverById(Long driverId) throws Exception {
        Optional<Driver> driver = driverRepository.findById(driverId);
        if(driver.isPresent()){
            return driver.get();
        }else{
            throw new Exception("Driver is not found" + driverId);
        }

    }

    @Override
    public List<Driver> getAll() {
        return driverRepository.findAll();
    }
}
