package com.mertoatakan.restaurantservice.entity;

import com.mertoatakan.restaurantservice.general.BaseEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@SolrDocument(solrCoreName = "restaurant_service_restaurants")
public class Restaurant extends BaseEntity {

    @Id
    @Indexed(name = "id", type = "string")
    @NotBlank
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

    @Indexed(name = "location_restaurant", type = "location_restaurant")
    private String location;


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

    public Restaurant(String id, String name, String description, Double latitude, Double longitude, String location, String address, Double averageRate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
        this.address = address;
        this.averageRate = averageRate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(Double latitude, Double longitude) {
        if (latitude != null && longitude != null) {
            this.location = latitude + "," + longitude;
        } else {
            this.location = null;
        }
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
