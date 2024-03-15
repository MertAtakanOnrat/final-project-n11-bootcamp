package com.mertoatakan.restaurantservice.controller.contract.impl;

import com.mertoatakan.restaurantservice.controller.contract.RestaurantControllerContract;
import com.mertoatakan.restaurantservice.dto.RestaurantDTO;
import com.mertoatakan.restaurantservice.entity.Restaurant;
import com.mertoatakan.restaurantservice.mapper.RestaurantMapper;
import com.mertoatakan.restaurantservice.request.RestaurantSaveRequest;
import com.mertoatakan.restaurantservice.service.entityService.RestaurantEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantControllerContractImpl implements RestaurantControllerContract {

    private final RestaurantEntityService restaurantEntityService;

    public Iterable<RestaurantDTO> getAllRestaurants(){
        Iterable<Restaurant> restaurants = restaurantEntityService.findAll();
        return RestaurantMapper.INSTANCE.convertToRestaurantDTOs(restaurants);
    }
    
    public RestaurantDTO getRestaurantById(String id){
        Restaurant restaurant = restaurantEntityService.findByIdWithControl(id);
        return RestaurantMapper.INSTANCE.convertToRestaurantDTO(restaurant);
    }

    public RestaurantDTO saveRestaurant(RestaurantSaveRequest request){
        Restaurant restaurant = RestaurantMapper.INSTANCE.convertToRestaurant(request);

        restaurant  = restaurantEntityService.save(restaurant);

        return RestaurantMapper.INSTANCE.convertToRestaurantDTO(restaurant);
    }
}
