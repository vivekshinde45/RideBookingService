package com.future_ride.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.future_ride.dto.RiderCreateRequest;
import com.future_ride.entities.Rider;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<RiderCreateRequest, Rider>() {

            @Override
            protected void configure() {
                map().setPhone(source.getPhoneNumber());
            }

        });

        return modelMapper;
    }
}
