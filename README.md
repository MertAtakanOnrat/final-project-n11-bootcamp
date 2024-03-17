# Restaurant Recommendation System

This project is designed according to the specifications requested in the assignment. It includes various services for users and restaurants, including reviews.

## Features

### User and Review Service

1. **User Operations:**
    - Users can be registered with name, surname, latitude, and longitude values.
    - User information can be updated, and users can be deleted.

2. **Review Operations:**
    - Reviews must have a score between 1 and 5.
    - Reviews can be added, updated, and deleted.

3. **Restaurant Recommendation API:**
    - Provides 3 restaurant recommendations considering the user's location and the restaurant's rating.
    - Restaurants further than 10 km should not be recommended.
    - The proximity has a 30% weight, while the restaurant rating has a 70% weight in the recommendation algorithm.

### Restaurant Service

- Restaurants can be registered with required information along with latitude and longitude values.
- Restaurants can be listed and are stored in Apache Solr.

### Technical Details

- **Apache Solr Usage:**
    - `solr.LatLonPointSpatialField` is used for restaurant data.
    - A custom field type was added to the `managed-schema.xml` file:

      ```xml
      <!-- Add the custom field type -->
      <fieldType name="location_restaurant" class="solr.LatLonPointSpatialField" docValues="true"/>
  
      <!-- Add the field -->
      <field name="location" type="location_restaurant" indexed="true" stored="true"/>
      ```

- **Asynchronous Operations:**
    - The `getRecommendation` function, among other crucial functions, is defined asynchronously using asynchronous annotations to enhance performance and scalability.

- **Geofilter Function:**
    - The geofilter function plays a critical role in efficiently querying restaurants within a 10km radius, significantly speeding up the recommendation process by utilizing spatial filtering.

- **Docker and Microservices:**
    - The project consists of three microservices: user-service, restaurant-service, and logging-service.
    - Docker-compose is used to manage all services.

- **Exception Handling:**
    - Custom exceptions are managed using `ControllerAdvice` and `ExceptionHandler` annotations.

- **Swagger UI:**
    - Swagger UI is utilized for easy application use and documentation.
    - User Service: `http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/`
    - Restaurant Service: `http://localhost:8081/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/`

### Docker Usage

- Docker-compose is used for Kafka, Zookeeper, Solr, user-service, restaurant-service, logging-service, and databases.
- Services can be managed with `docker-compose build --no-cache` and `docker-compose up -d` commands.

### Adding Screenshots of the Running Application

To include screenshots in your documentation, follow these steps:

![image](https://drive.google.com/uc?export=view&id=<FILE_ID>)


