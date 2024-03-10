package com.mertoatakan.userservice.dto;

import com.mertoatakan.userservice.enums.Rate;

import java.time.LocalDateTime;

public record UserReviewDTO(Long id,
                            Long userId,
                            Long restaurantId,
                            String comment,
                            Rate rate,
                            LocalDateTime reviewDate) {
}
