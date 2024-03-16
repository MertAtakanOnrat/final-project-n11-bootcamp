package com.mertoatakan.restaurantservice.dto;

import com.mertoatakan.restaurantservice.enums.Rate;

import java.time.LocalDateTime;

public record UserReviewDTO(Long id,
                            Long userId,
                            String restaurantId,
                            String comment,
                            Rate rate,
                            LocalDateTime reviewDate) {
}
