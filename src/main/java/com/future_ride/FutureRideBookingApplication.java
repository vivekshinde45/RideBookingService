package com.future_ride;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FutureRideBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FutureRideBookingApplication.class, args);
	}

}
