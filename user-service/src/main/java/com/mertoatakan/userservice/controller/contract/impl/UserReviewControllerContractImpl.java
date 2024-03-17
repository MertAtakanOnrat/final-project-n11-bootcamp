package com.mertoatakan.userservice.controller.contract.impl;

import com.mertoatakan.userservice.client.RestaurantClient;
import com.mertoatakan.userservice.controller.contract.UserReviewControllerContract;
import com.mertoatakan.userservice.dto.UserReviewDTO;
import com.mertoatakan.userservice.entity.UserReview;
import com.mertoatakan.userservice.enums.Rate;
import com.mertoatakan.userservice.errormessage.RestaurantErrorMessage;
import com.mertoatakan.userservice.errormessage.UserErrorMessage;
import com.mertoatakan.userservice.exceptions.RestaurantNotFoundException;
import com.mertoatakan.userservice.exceptions.UserNotFoundException;
import com.mertoatakan.userservice.mapper.UserReviewMapper;
import com.mertoatakan.userservice.request.UserReviewSaveRequest;
import com.mertoatakan.userservice.request.UserReviewUpdateRequest;
import com.mertoatakan.userservice.service.RestaurantService;
import com.mertoatakan.userservice.service.entityService.UserEntityService;
import com.mertoatakan.userservice.service.entityService.UserReviewEntityService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserReviewControllerContractImpl implements UserReviewControllerContract {

    private final UserReviewEntityService userReviewEntityService;
    private final UserEntityService userEntityService;
    private final RestaurantClient restaurantClient;
    private final RestaurantService restaurantService;

    @Override
    public List<UserReviewDTO> getAllUserReviews() {
        List<UserReview> userReviews = userReviewEntityService.findAll();
        return UserReviewMapper.INSTANCE.convertToUserReviewDTOs(userReviews);
    }

    @Override
    public UserReviewDTO getUserReviewById(Long id) {
        UserReview userReview = userReviewEntityService.findByIdWithControl(id);
        return UserReviewMapper.INSTANCE.convertToUserReviewDTO(userReview);
    }

    @Override
    public List<UserReviewDTO> getAllUserReviewsByRestaurantId(String id) {
        checkRestaurantExists(id);

        List<UserReview> userReviews = userReviewEntityService.findAllByRestaurantId(id);
        return UserReviewMapper.INSTANCE.convertToUserReviewDTOs(userReviews);
    }

    @Override
    public List<UserReviewDTO> getAllUserReviewsByUserId(Long id) {
        checkUserExists(id);

        List<UserReview> userReviews = userReviewEntityService.findAllByUserId(id);
        return UserReviewMapper.INSTANCE.convertToUserReviewDTOs(userReviews);
    }


    @Override
    public UserReviewDTO saveUserReview(UserReviewSaveRequest request) {

        checkUserExists(request.userId());
        checkRestaurantExists(request.restaurantId());

        Rate rate = Rate.fromValue(request.rate());
        UserReview userReview = UserReviewMapper.INSTANCE.convertToUserReview(request);
        userReview.setRate(rate);
        userReview.setReviewDate(LocalDateTime.now());
        userReview = userReviewEntityService.save(userReview);

        restaurantService.getAverageRateAsync(userReview.getRestaurantId());

        return UserReviewMapper.INSTANCE.convertToUserReviewDTO(userReview);
    }

    @Override
    public UserReviewDTO updateUserReview(UserReviewUpdateRequest request) {
        UserReview userReview = userReviewEntityService.findByIdWithControl(request.id());
        UserReviewMapper.INSTANCE.updateUserReviewFields(userReview, request);

        userReviewEntityService.save(userReview);

        restaurantService.getAverageRateAsync(userReview.getRestaurantId());

        return UserReviewMapper.INSTANCE.convertToUserReviewDTO(userReview);
    }

    @Override
    public void deleteUserReview(Long id) {
        UserReview userReview = userReviewEntityService.findByIdWithControl(id);
        userReviewEntityService.delete(userReview.getId());
        restaurantService.getAverageRateAsync(userReview.getRestaurantId());
    }

    private void checkRestaurantExists(String restaurantId) {
        try {
            restaurantClient.checkRestaurantExists(restaurantId);
        } catch (FeignException.NotFound e) {
            throw new RestaurantNotFoundException(RestaurantErrorMessage.RESTAURANT_NOT_FOUND);
        }
    }

    private void checkUserExists(Long userId) {
        try {
            userEntityService.findByIdWithControl(userId);
        } catch (Exception e) {
            throw new UserNotFoundException(UserErrorMessage.USER_NOT_FOUND);
        }
    }
}
