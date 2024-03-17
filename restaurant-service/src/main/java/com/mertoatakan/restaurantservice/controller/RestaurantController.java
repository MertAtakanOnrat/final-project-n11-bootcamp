package com.mertoatakan.restaurantservice.controller;

import com.mertoatakan.restaurantservice.controller.contract.RestaurantControllerContract;
import com.mertoatakan.restaurantservice.dto.RestaurantDTO;
import com.mertoatakan.restaurantservice.general.RestResponse;
import com.mertoatakan.restaurantservice.request.RestaurantSaveRequest;
import com.mertoatakan.restaurantservice.request.RestaurantUpdateRequest;
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

    private final RestaurantControllerContract restaurantControllerContract;

    public RestaurantController(RestaurantControllerContract restaurantControllerContract) {
        this.restaurantControllerContract = restaurantControllerContract;
    }


    @GetMapping
    public ResponseEntity<RestResponse<Iterable<RestaurantDTO>>> getAllRestaurants(){
        return ResponseEntity.ok(RestResponse.of(restaurantControllerContract.getAllRestaurants()));
    }

    @GetMapping("/average-rate/{id}")
    public ResponseEntity<RestResponse<Double>> getAverageRate(@PathVariable @NotNull String id){
        Double averageRate = restaurantControllerContract.getAverageRate(id);
        return ResponseEntity.ok(RestResponse.of(averageRate));
    }

    @GetMapping("{id}")
    public ResponseEntity<RestResponse<RestaurantDTO>> getRestaurantById(@PathVariable @NotNull String id){
        RestaurantDTO restaurantDTO = restaurantControllerContract.getRestaurantById(id);
        return ResponseEntity.ok(RestResponse.of(restaurantDTO));
    }

    @GetMapping("/restaurants-near-to-user/{id}")
    public ResponseEntity<RestResponse<Iterable<RestaurantDTO>>> getRestaurantsNear(@PathVariable Long id){
        Iterable<RestaurantDTO> restaurantsNear = restaurantControllerContract.getRestaurantsNear(id);
        return ResponseEntity.ok(RestResponse.of(restaurantsNear));
    }

    @PostMapping
    public ResponseEntity<RestResponse<RestaurantDTO>> saveRestaurant(@Valid @RequestBody RestaurantSaveRequest request){
        RestaurantDTO restaurantDTO = restaurantControllerContract.saveRestaurant(request);
        return ResponseEntity.ok(RestResponse.of(restaurantDTO));
    }

    @PostMapping("/{id}")
    public ResponseEntity<RestResponse<RestaurantDTO>> updateRestaurant(@RequestBody RestaurantUpdateRequest request){
        RestaurantDTO restaurantDTO = restaurantControllerContract.updateRestaurant(request);
        return ResponseEntity.ok(RestResponse.of(restaurantDTO));

    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable String id){
        restaurantControllerContract.deleteRestaurant(id);
    }
}
