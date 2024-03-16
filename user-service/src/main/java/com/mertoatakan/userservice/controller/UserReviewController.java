package com.mertoatakan.userservice.controller;

import com.mertoatakan.userservice.controller.contract.UserReviewControllerContract;
import com.mertoatakan.userservice.dto.UserReviewDTO;
import com.mertoatakan.userservice.general.RestResponse;
import com.mertoatakan.userservice.request.UserReviewSaveRequest;
import com.mertoatakan.userservice.request.UserReviewUpdateRequest;
import com.mertoatakan.userservice.request.UserSaveRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/user-reviews")
@Validated
@Tag(name = "User Review Controller", description = "User Review Management")
public class UserReviewController {

    private final UserReviewControllerContract userReviewControllerContract;
    public UserReviewController(UserReviewControllerContract userReviewControllerContract) {
        this.userReviewControllerContract = userReviewControllerContract;
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<UserReviewDTO>>> getAllUserReviews(){
        List<UserReviewDTO> userReviewDTOs = userReviewControllerContract.getAllUserReviews();
        return ResponseEntity.ok(RestResponse.of(userReviewDTOs));
    }

    @GetMapping("{id}")
    public ResponseEntity<RestResponse<UserReviewDTO>> getUserReviewById(@PathVariable Long id){
        UserReviewDTO userReviewDTO = userReviewControllerContract.getUserReviewById(id);
        return ResponseEntity.ok(RestResponse.of(userReviewDTO));
    }

    @GetMapping("/with-restaurant-id/{restaurantId}")
    public ResponseEntity<RestResponse<List<UserReviewDTO>>> getAllUserReviewsByRestaurantId(@PathVariable String restaurantId){
        List<UserReviewDTO> userReviewDTOs = userReviewControllerContract.getAllUserReviewsByRestaurantId(restaurantId);
        return ResponseEntity.ok(RestResponse.of(userReviewDTOs));
    }

    @GetMapping("/with-user-id/{userId}")
    public ResponseEntity<RestResponse<List<UserReviewDTO>>> getAllUserReviewsByUserId(@PathVariable Long userId){
        List<UserReviewDTO> userReviewDTOs = userReviewControllerContract.getAllUserReviewsByUserId(userId);
        return ResponseEntity.ok(RestResponse.of(userReviewDTOs));
    }

    @PostMapping
    public ResponseEntity<RestResponse<UserReviewDTO>> saveUserReview(@Valid @RequestBody UserReviewSaveRequest request){
        UserReviewDTO userReviewDTO = userReviewControllerContract.saveUserReview(request);
        return ResponseEntity.ok(RestResponse.of(userReviewDTO));
    }

    @PostMapping("/{id}")
    public ResponseEntity<RestResponse<UserReviewDTO>> updateUserReview(@RequestBody UserReviewUpdateRequest request){
        UserReviewDTO userReviewDTO = userReviewControllerContract.updateUserReview(request);
        return ResponseEntity.ok(RestResponse.of(userReviewDTO));
    }
    @DeleteMapping("/{id}")
    public void deleteUserReview(@Valid @PathVariable Long id){
        userReviewControllerContract.deleteUserReview(id);
    }
}
