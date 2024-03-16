package com.mertoatakan.userservice.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record UserReviewUpdateRequest(@NotNull @Positive Long id,
                                      String comment,
                                      @NotNull Integer rate) {
}
