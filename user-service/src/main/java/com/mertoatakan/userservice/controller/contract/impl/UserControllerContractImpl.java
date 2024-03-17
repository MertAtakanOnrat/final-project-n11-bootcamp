package com.mertoatakan.userservice.controller.contract.impl;

import com.mertoatakan.userservice.controller.contract.UserControllerContract;
import com.mertoatakan.userservice.dto.RestaurantDTO;
import com.mertoatakan.userservice.dto.UserDTO;
import com.mertoatakan.userservice.dto.UserLocationDTO;
import com.mertoatakan.userservice.entity.User;
import com.mertoatakan.userservice.errormessage.UserErrorMessage;
import com.mertoatakan.userservice.general.BusinessException;
import com.mertoatakan.userservice.general.RestResponse;
import com.mertoatakan.userservice.mapper.UserMapper;
import com.mertoatakan.userservice.request.UserSaveRequest;
import com.mertoatakan.userservice.request.UserUpdatePasswordRequest;
import com.mertoatakan.userservice.request.UserUpdateRequest;
import com.mertoatakan.userservice.request.UserUpdateStatusRequest;
import com.mertoatakan.userservice.service.RestaurantService;
import com.mertoatakan.userservice.service.entityService.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserControllerContractImpl implements UserControllerContract {

    private final UserEntityService userEntityService;
    private final RestaurantService restaurantService;
    @Override
    public UserDTO saveUser(UserSaveRequest request) {
        User user = UserMapper.INSTANCE.convertToUser(request);
        user = userEntityService.save(user);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userEntityService.findAll();
        return UserMapper.INSTANCE.convertToUserDTOs(users);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userEntityService.findByIdWithControl(id);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public UserDTO getUserByIdAndName(Long id, String name) {
        User user = userEntityService.findByIdAndName(id, name);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public UserLocationDTO getUserLocation(Long id) {
        User user = userEntityService.findByIdWithControl(id);
        return UserMapper.INSTANCE.convertToUserLocationDTO(user);
    }

    @Override
    public List<RestaurantDTO> getRecommendation(Long userId) {

        try {
            CompletableFuture<ResponseEntity<RestResponse<Iterable<RestaurantDTO>>>> restaurantsNearFuture =
                    restaurantService.getRestaurantsNearAsync(userId);

            ResponseEntity<RestResponse<Iterable<RestaurantDTO>>> responseEntity = restaurantsNearFuture.get();
            List<RestaurantDTO> restaurantDTOs = new ArrayList<>();
            responseEntity.getBody().getData().forEach(restaurantDTOs::add);

            return calculateRecommendations(userId, restaurantDTOs);
        } catch (InterruptedException | ExecutionException e) {

            Thread.currentThread().interrupt();
            throw new RuntimeException("Unable to complete the async operation", e);
        }
    }

    private List<RestaurantDTO> calculateRecommendations(Long userId, List<RestaurantDTO> restaurants) {
        User user = userEntityService.findByIdWithControl(userId);
        double userLat = user.getLatitude();
        double userLon = user.getLongitude();

        return restaurants.stream()
                .map(restaurant -> {
                    double distanceScore = calculateDistanceScore(userLat, userLon, restaurant.latitude(), restaurant.longitude());
                    double weightedScore = restaurant.averageRate() * 0.7 + distanceScore * 0.3;
                    return new AbstractMap.SimpleEntry<>(restaurant, weightedScore);
                })
                .sorted(Map.Entry.<RestaurantDTO, Double>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private double calculateDistanceScore(double userLat, double userLon, double restaurantLat, double restaurantLon) {
        double earthRadiusKm = 6371.0;

        double dLat = Math.toRadians(restaurantLat - userLat);
        double dLon = Math.toRadians(restaurantLon - userLon);

        userLat = Math.toRadians(userLat);
        restaurantLat = Math.toRadians(restaurantLat);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.sin(dLon / 2) * Math.sin(dLon / 2) *
                        Math.cos(userLat) * Math.cos(restaurantLat);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distanceKm = earthRadiusKm * c;

        double score = Math.max(0, (10 - distanceKm) / 10.0);
        return score;
    }



    @Override
    public UserDTO updateUser(Long id, UserUpdateRequest request) {
        User user = userEntityService.findByIdWithControl(id);
        user = UserMapper.INSTANCE.updateUser(request, user);
        user = userEntityService.save(user);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public UserDTO updateUserStatus(Long id, UserUpdateStatusRequest request) {
        User user = userEntityService.findByIdWithControl(id);
        user.setUserStatus(request.userStatus());
        user = userEntityService.save(user);
        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public UserDTO updateUserPassword(Long id, UserUpdatePasswordRequest request) {
        User user = userEntityService.findByIdWithControl(id);

        if (!user.getPassword().equals(request.oldPass())) {
            throw new BusinessException(UserErrorMessage.INVALID_OLD_PASSWORD);
        }

        if (!request.newPass().equals(request.newPassRepeat())) {
            throw new BusinessException(UserErrorMessage.NEW_PASSWORDS_DID_NOT_MATCH);
        }

        user.setPassword(request.newPass());
        userEntityService.save(user);

        return UserMapper.INSTANCE.convertToUserDTO(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userEntityService.deleteUserById(id);
    }
}
