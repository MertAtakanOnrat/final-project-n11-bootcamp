package com.mertoatakan.restaurantservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RestaurantserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantserviceApplication.class, args);
	}

}
