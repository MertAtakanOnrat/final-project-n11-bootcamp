package com.mertoatakan.restaurantservice.controller.contract;

import com.mertoatakan.restaurantservice.dto.RestaurantDTO;
import com.mertoatakan.restaurantservice.request.RestaurantSaveRequest;

public interface RestaurantControllerContract {
    Iterable<RestaurantDTO> getAllRestaurants();
    RestaurantDTO getRestaurantById(String id);
    RestaurantDTO saveRestaurant(RestaurantSaveRequest request);
    Double getAverageRate(String id);
    void deleteRestaurant(String id);

}
