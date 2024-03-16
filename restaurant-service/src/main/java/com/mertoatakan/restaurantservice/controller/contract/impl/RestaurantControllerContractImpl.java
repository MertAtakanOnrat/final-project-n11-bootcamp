package com.mertoatakan.restaurantservice.controller.contract.impl;

import com.mertoatakan.restaurantservice.client.UserReviewClient;
import com.mertoatakan.restaurantservice.controller.contract.RestaurantControllerContract;
import com.mertoatakan.restaurantservice.dto.RestaurantDTO;
import com.mertoatakan.restaurantservice.dto.UserReviewDTO;
import com.mertoatakan.restaurantservice.entity.Restaurant;
import com.mertoatakan.restaurantservice.general.RestResponse;
import com.mertoatakan.restaurantservice.mapper.RestaurantMapper;
import com.mertoatakan.restaurantservice.request.RestaurantSaveRequest;
import com.mertoatakan.restaurantservice.service.entityService.RestaurantEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantControllerContractImpl implements RestaurantControllerContract {

    private final RestaurantEntityService restaurantEntityService;
    private final UserReviewClient userReviewClient;

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

    @Override
    public Double getAverageRate(String id) {
        Double averageRateForRestaurant = calculateAverageRateForRestaurant(id);

        Restaurant restaurant = restaurantEntityService.findByIdWithControl(id);
        restaurant.setAverageRate(averageRateForRestaurant);
        restaurantEntityService.save(restaurant);

        return averageRateForRestaurant;
    }

    @Override
    public void deleteRestaurant(String id) {
        restaurantEntityService.delete(id);
    }

    public Double calculateAverageRateForRestaurant(String restaurantId) {
        ResponseEntity<RestResponse<List<UserReviewDTO>>> responseEntity = userReviewClient.getAllUserReviewsByRestaurantId(restaurantId);
        List<UserReviewDTO> reviews = responseEntity.getBody().getData();
        if (reviews.isEmpty()) {
            return 0.0;
        }

        double sum = reviews.stream()
                .mapToInt(review -> review.rate().getValue())
                .sum();

        double average = sum / reviews.size();
        BigDecimal bd = new BigDecimal(average).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
