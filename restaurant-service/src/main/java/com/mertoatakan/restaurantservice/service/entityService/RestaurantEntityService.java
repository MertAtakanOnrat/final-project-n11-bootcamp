package com.mertoatakan.restaurantservice.service.entityService;

import com.mertoatakan.restaurantservice.entity.Restaurant;
import com.mertoatakan.restaurantservice.general.BaseEntityService;
import com.mertoatakan.restaurantservice.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

@Service
public class RestaurantEntityService extends BaseEntityService<Restaurant, RestaurantRepository> {
    protected RestaurantEntityService(RestaurantRepository repository) {
        super(repository);
    }
}
