package com.mertoatakan.restaurantservice.repository;

import com.mertoatakan.restaurantservice.entity.Restaurant;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface RestaurantRepository extends SolrCrudRepository<Restaurant, String> {

    @Query("{!geofilt sfield=location_restaurant pt=?0,?1 d=10}")
    Iterable<Restaurant> findRestaurantsNear(Double lat,Double lng);

}
