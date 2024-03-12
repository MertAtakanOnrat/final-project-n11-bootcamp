package com.mertoatakan.restaurantservice.mapper;

import com.mertoatakan.restaurantservice.dto.RestaurantDTO;
import com.mertoatakan.restaurantservice.entity.Restaurant;
import com.mertoatakan.restaurantservice.request.RestaurantSaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    Restaurant convertToRestaurant(RestaurantSaveRequest request);

    RestaurantDTO convertToRestaurantDTO(Restaurant customer);

    Iterable<RestaurantDTO> convertToRestaurantDTOs(Iterable<Restaurant> restaurants);
}
