package com.mertoatakan.restaurantservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-service", url = "http://localhost:8080/api/v1/user-reviews")
public interface UserReviewClient {

}
