package com.mertoatakan.restaurantservice.request;

public record RestaurantSaveRequest(String name,
                                    String description,
                                    Double latitude,
                                    Double longitude,
                                    String address,
                                    Double averageRate) {
}
