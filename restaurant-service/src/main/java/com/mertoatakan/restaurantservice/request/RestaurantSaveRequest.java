package com.mertoatakan.restaurantservice.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record RestaurantSaveRequest(@NotBlank @Length(min = 1, max = 100) String name,
                                    @NotBlank String description,
                                    @NotNull Double latitude,
                                    @NotNull Double longitude,
                                    @NotBlank String address) {
}
