package com.mertoatakan.restaurantservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@SolrDocument(solrCoreName = "restaurant_service_restaurants")
public class Restaurant {

    @Id
    @Indexed(name = "id", type = "string")
    private String id;

    @NotBlank
    @Indexed(name = "name", type = "string")
    private String name;

    @NotBlank
    @Indexed(name = "description", type = "string")
    private String description;

    @NotNull
    @DecimalMin(value = "-90.0", inclusive = true)
    @DecimalMax(value = "90.0", inclusive = true)
    @Indexed(name = "latitude", type = "pdouble")
    private Double latitude;

    @NotNull
    @DecimalMin(value = "-180.0", inclusive = true)
    @DecimalMax(value = "180.0", inclusive = true)
    @Indexed(name = "longitude", type = "pdouble")
    private Double longitude;

    @NotBlank
    @Indexed(name = "address", type = "string")
    private String address;

    @NotNull
    @DecimalMin(value = "1.0", inclusive = true)
    @DecimalMax(value = "5.0", inclusive = true)
    @Indexed(name = "average_rate", type = "pdouble")
    private Double averageRate;

    public Restaurant() {
    }

    public Restaurant(String id, String name, String description, Double latitude, Double longitude, String address, Double averageRate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.averageRate = averageRate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(Double averageScore) {
        this.averageRate = averageScore;
    }
}
