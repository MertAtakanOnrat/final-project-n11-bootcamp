package com.mertoatakan.userservice.service;

import com.mertoatakan.userservice.client.RestaurantClient;
import com.mertoatakan.userservice.dto.RestaurantDTO;
import com.mertoatakan.userservice.general.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantClient restaurantClient;

    @Async
    public CompletableFuture<ResponseEntity<RestResponse<Double>>> getAverageRateAsync(String id) {
        ResponseEntity<RestResponse<Double>> restResponseResponseEntity = restaurantClient.getAverageRate(id);
        return CompletableFuture.completedFuture(restResponseResponseEntity);
    }

    @Async
    public CompletableFuture<ResponseEntity<RestResponse<Iterable<RestaurantDTO>>>> getRestaurantsNearAsync(Long userId) {
        ResponseEntity<RestResponse<Iterable<RestaurantDTO>>> response = restaurantClient.getRestaurantsNear(userId);
        return CompletableFuture.completedFuture(response);
    }
}
