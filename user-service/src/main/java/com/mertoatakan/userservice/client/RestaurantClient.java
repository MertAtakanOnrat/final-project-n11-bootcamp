package com.mertoatakan.userservice.client;

import com.mertoatakan.userservice.dto.RestaurantDTO;
import com.mertoatakan.userservice.general.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotNull;

@FeignClient(name = "restaurant-service", url = "http://localhost:8081/api/v1/restaurants")
public interface RestaurantClient {
    @GetMapping("/{id}")
    ResponseEntity<RestResponse<RestaurantDTO>> checkRestaurantExists(@PathVariable("id") String id);

    @GetMapping("/average-rate/{id}")
    ResponseEntity<RestResponse<Double>> getAverageRate(@PathVariable @NotNull String id);

    @GetMapping("/restaurants-near-to-user/{id}")
    ResponseEntity<RestResponse<Iterable<RestaurantDTO>>> getRestaurantsNear(@PathVariable Long id);
}


