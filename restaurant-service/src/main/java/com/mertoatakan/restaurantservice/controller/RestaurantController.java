package com.mertoatakan.restaurantservice.controller;

import com.mertoatakan.restaurantservice.dto.RestaurantDTO;
import com.mertoatakan.restaurantservice.general.RestResponse;
import com.mertoatakan.restaurantservice.request.RestaurantSaveRequest;
import com.mertoatakan.restaurantservice.service.impl.RestaurantServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/restaurants")
public class RestaurantController {

    private final RestaurantServiceImpl restaurantService;

    public RestaurantController(RestaurantServiceImpl restaurantService) {
        this.restaurantService = restaurantService;
    }


    @GetMapping
    public ResponseEntity<RestResponse<Iterable<RestaurantDTO>>> getAllRestaurants(){
        return ResponseEntity.ok(RestResponse.of(restaurantService.getAllRestaurants()));
    }

    @GetMapping("{id}")
    public ResponseEntity<RestResponse<RestaurantDTO>> getRestaurantById(@PathVariable String id){
        RestaurantDTO restaurantDTO = restaurantService.getRestaurantById(id);
        return ResponseEntity.ok(RestResponse.of(restaurantDTO));
    }

    @PostMapping
    public ResponseEntity<RestResponse<RestaurantDTO>> saveRestaurant(@RequestBody RestaurantSaveRequest request){
        RestaurantDTO restaurantDTO = restaurantService.saveRestaurant(request);
        return ResponseEntity.ok(RestResponse.of(restaurantDTO));
    }
}
