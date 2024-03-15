package com.mertoatakan.userservice.repository;

import com.mertoatakan.userservice.entity.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {

    List<UserReview> findAllByRestaurantId(String id);

    List<UserReview> findAllByUserId(Long id);
}
