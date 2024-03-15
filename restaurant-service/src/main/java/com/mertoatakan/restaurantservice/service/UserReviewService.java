package com.mertoatakan.restaurantservice.service;

import com.mertoatakan.restaurantservice.client.UserReviewClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserReviewService {

    private final UserReviewClient userReviewClient;

}
