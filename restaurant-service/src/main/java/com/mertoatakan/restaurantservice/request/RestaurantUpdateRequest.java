package com.mertoatakan.restaurantservice.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record RestaurantUpdateRequest(@NotBlank String id,
                                      @NotBlank @Length(min = 1, max = 100) String name,
                                      @NotBlank String description,
                                      @NotNull Double latitude,
                                      @NotNull Double longitude,
                                      @NotBlank String address) {
}
