package com.mertoatakan.restaurantservice.mapper;

import com.mertoatakan.restaurantservice.dto.RestaurantDTO;
import com.mertoatakan.restaurantservice.entity.Restaurant;
import com.mertoatakan.restaurantservice.request.RestaurantSaveRequest;
import com.mertoatakan.restaurantservice.request.RestaurantUpdateRequest;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    @Mapping(target = "averageRate", ignore = true)
    Restaurant convertToRestaurant(RestaurantSaveRequest request);


    RestaurantDTO convertToRestaurantDTO(Restaurant restaurant);

    Iterable<RestaurantDTO> convertToRestaurantDTOs(Iterable<Restaurant> restaurants);

    @Mapping(target = "id", ignore = true)
    void updateRestaurantFields(@MappingTarget Restaurant restaurant, RestaurantUpdateRequest request);

}
