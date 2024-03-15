package com.mertoatakan.userservice.request;

import com.mertoatakan.userservice.enums.Rate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record UserReviewSaveRequest(@NotNull Long userId,
                                    @NotNull String restaurantId,
                                    String comment,
                                    @NotNull Integer rate) {
}
