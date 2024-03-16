package com.mertoatakan.userservice.service;

import com.mertoatakan.userservice.client.RestaurantClient;
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
    public CompletableFuture<ResponseEntity<RestResponse<Double>>> updateAverageRateAsync(String id) {
        ResponseEntity<RestResponse<Double>> restResponseResponseEntity = restaurantClient.updateAverageRate(id);
        return CompletableFuture.completedFuture(restResponseResponseEntity);
    }
}
