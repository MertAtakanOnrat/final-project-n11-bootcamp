package com.mertoatakan.restaurantservice.service;

import com.mertoatakan.restaurantservice.dto.RestaurantDTO;
import com.mertoatakan.restaurantservice.request.RestaurantSaveRequest;

public interface RestaurantService {
    Iterable<RestaurantDTO> getAllRestaurants();
    RestaurantDTO getRestaurantById(String id);
    RestaurantDTO saveRestaurant(RestaurantSaveRequest request);
}
