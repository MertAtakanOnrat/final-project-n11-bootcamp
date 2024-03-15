package com.mertoatakan.restaurantservice.controller;

import com.mertoatakan.restaurantservice.dto.RestaurantDTO;
import com.mertoatakan.restaurantservice.general.RestResponse;
import com.mertoatakan.restaurantservice.request.RestaurantSaveRequest;
import com.mertoatakan.restaurantservice.controller.contract.impl.RestaurantControllerContractImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("api/v1/restaurants")
@Validated
@Tag(name = "Restaurant Controller", description = "Restaurant Management")
public class RestaurantController {

    private final RestaurantControllerContractImpl restaurantService;

    public RestaurantController(RestaurantControllerContractImpl restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public ResponseEntity<RestResponse<Iterable<RestaurantDTO>>> getAllRestaurants(){
        return ResponseEntity.ok(RestResponse.of(restaurantService.getAllRestaurants()));
    }

    @GetMapping("{id}")
    public ResponseEntity<RestResponse<RestaurantDTO>> getRestaurantById(@PathVariable @NotNull String id){
        RestaurantDTO restaurantDTO = restaurantService.getRestaurantById(id);
        return ResponseEntity.ok(RestResponse.of(restaurantDTO));
    }

    @PostMapping
    public ResponseEntity<RestResponse<RestaurantDTO>> saveRestaurant(@Valid @RequestBody RestaurantSaveRequest request){
        RestaurantDTO restaurantDTO = restaurantService.saveRestaurant(request);
        return ResponseEntity.ok(RestResponse.of(restaurantDTO));
    }
}
