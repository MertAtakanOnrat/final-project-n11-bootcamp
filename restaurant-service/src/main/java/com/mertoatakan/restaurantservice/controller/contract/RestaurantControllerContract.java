package com.mertoatakan.restaurantservice.controller.contract;

import com.mertoatakan.restaurantservice.dto.RestaurantDTO;
import com.mertoatakan.restaurantservice.dto.UserLocationDTO;
import com.mertoatakan.restaurantservice.request.RestaurantSaveRequest;
import com.mertoatakan.restaurantservice.request.RestaurantUpdateRequest;

public interface RestaurantControllerContract {
    Iterable<RestaurantDTO> getAllRestaurants();
    RestaurantDTO getRestaurantById(String id);
    RestaurantDTO saveRestaurant(RestaurantSaveRequest request);
    Double getAverageRate(String id);
    Iterable<RestaurantDTO> getRestaurantsNear(Long id);
    UserLocationDTO getUserLocation(Long id);
    RestaurantDTO updateRestaurant(RestaurantUpdateRequest request);
    void deleteRestaurant(String id);

}
