package com.mertoatakan.restaurantservice.client;

import com.mertoatakan.restaurantservice.dto.UserReviewDTO;
import com.mertoatakan.restaurantservice.general.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user-service", url = "http://localhost:8080/api/v1/user-reviews")
public interface UserReviewClient {

    @GetMapping("/with-restaurant-id/{id}")
    ResponseEntity<RestResponse<List<UserReviewDTO>>> getAllUserReviewsByRestaurantId(@PathVariable String id);

}
