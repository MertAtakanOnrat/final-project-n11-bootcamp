package com.mertoatakan.restaurantservice.dto;


public record RestaurantDTO(String id,
                            String name,
                            String description,
                            Double latitude,
                            Double longitude,
                            String location,
                            String address,
                            Double averageRate) {
}
