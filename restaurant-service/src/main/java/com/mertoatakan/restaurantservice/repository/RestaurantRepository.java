package com.mertoatakan.restaurantservice.repository;

import com.mertoatakan.restaurantservice.entity.Restaurant;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface RestaurantRepository extends SolrCrudRepository<Restaurant, String> {
}
