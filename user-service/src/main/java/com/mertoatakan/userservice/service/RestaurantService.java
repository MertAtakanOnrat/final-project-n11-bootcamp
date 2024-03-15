package com.mertoatakan.userservice.service;

import com.mertoatakan.userservice.client.RestaurantClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantClient restaurantClient;
}
