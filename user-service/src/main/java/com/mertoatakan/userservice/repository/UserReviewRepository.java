package com.mertoatakan.userservice.repository;

import com.mertoatakan.userservice.entity.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
}
