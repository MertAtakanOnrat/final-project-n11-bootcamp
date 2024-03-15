package com.mertoatakan.userservice.controller.contract;

import com.mertoatakan.userservice.dto.UserDTO;
import com.mertoatakan.userservice.dto.UserReviewDTO;
import com.mertoatakan.userservice.request.UserReviewSaveRequest;
import com.mertoatakan.userservice.request.UserSaveRequest;

import java.util.List;

public interface UserReviewControllerContract {
    UserReviewDTO saveUserReview(UserReviewSaveRequest request);
    List<UserReviewDTO> getAllUserReviews();
    UserReviewDTO getUserReviewById(Long id);
    List<UserReviewDTO> getAllUserReviewsByRestaurantId(String id);
    List<UserReviewDTO> getAllUserReviewsByUserId(Long id);
}
