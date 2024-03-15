package com.mertoatakan.restaurantservice.service.entityService;

import com.mertoatakan.restaurantservice.entity.Restaurant;
import com.mertoatakan.restaurantservice.errormessage.RestaurantErrorMessage;
import com.mertoatakan.restaurantservice.exceptions.RestaurantNotFoundException;
import com.mertoatakan.restaurantservice.general.BaseEntityService;
import com.mertoatakan.restaurantservice.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantEntityService extends BaseEntityService<Restaurant, RestaurantRepository> {
    protected RestaurantEntityService(RestaurantRepository repository) {
        super(repository);
    }

    @Override
    public Restaurant findByIdWithControl(String id) {
        Optional<Restaurant> optionalE = getRepository().findById(id);
        Restaurant restaurant;
        if (optionalE.isPresent()){
            restaurant = optionalE.get();
        }
        else{
            throw new RestaurantNotFoundException(RestaurantErrorMessage.RESTAURANT_NOT_FOUND);
        }
        return restaurant;
    }
}
