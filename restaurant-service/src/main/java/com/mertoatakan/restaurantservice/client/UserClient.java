package com.mertoatakan.restaurantservice.client;

import com.mertoatakan.restaurantservice.dto.UserLocationDTO;
import com.mertoatakan.restaurantservice.general.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "user-service", url = "http://user-service:8080/api/v1/users")
public interface UserClient {

    @GetMapping("/user-location/{id}")
    ResponseEntity<RestResponse<UserLocationDTO>> getUserLocation(@PathVariable Long id);
}
