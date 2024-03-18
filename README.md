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

- Restaurants can be listed and are stored in Apache Solr.
- Restaurants can be added, updated, and deleted with necessary information including latitude and longitude values.
- Utilizes Apache Solr's geofilter to return restaurants within a 10km radius, enhancing search efficiency.

### Technical Details

- **Apache Solr Usage:**
    - `solr.LatLonPointSpatialField` is used for location data.
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
- Services can be managed with `docker-compose build --no-cache` and `docker-compose up -d` commands respectively.

### Adding Screenshots of the Running Application


![Screenshot 2](https://github.com/MertAtakanOnrat/final-project-n11-bootcamp/blob/main/screenshots/Screenshot%202024-03-17%20at%2023.01.01.png "Screenshot 2 Description")
![Screenshot 3](https://github.com/MertAtakanOnrat/final-project-n11-bootcamp/blob/main/screenshots/Screenshot%202024-03-17%20at%2023.01.48.png "Screenshot 3 Description")
![Screenshot 4](https://github.com/MertAtakanOnrat/final-project-n11-bootcamp/blob/main/screenshots/Screenshot%202024-03-17%20at%2023.01.56.png "Screenshot 4 Description")
![Screenshot 5](https://github.com/MertAtakanOnrat/final-project-n11-bootcamp/blob/main/screenshots/Screenshot%202024-03-17%20at%2023.02.02.png "Screenshot 5 Description")
![Screenshot 6](https://github.com/MertAtakanOnrat/final-project-n11-bootcamp/blob/main/screenshots/Screenshot%202024-03-17%20at%2023.02.12.png "Screenshot 6 Description")
![Screenshot 7](https://github.com/MertAtakanOnrat/final-project-n11-bootcamp/blob/main/screenshots/Screenshot%202024-03-18%20at%2023.59.16.png "Screenshot 7 Description")
![Screenshot 7](https://github.com/MertAtakanOnrat/final-project-n11-bootcamp/blob/main/screenshots/Screenshot%202024-03-17%20at%2023.02.46.png "Screenshot 7 Description")
![Screenshot 8](https://github.com/MertAtakanOnrat/final-project-n11-bootcamp/blob/main/screenshots/Screenshot%202024-03-17%20at%2023.50.21.png "Screenshot 8 Description")
![Screenshot 8](https://github.com/MertAtakanOnrat/final-project-n11-bootcamp/blob/main/screenshots/Screenshot%202024-03-17%20at%2023.03.48.png "Screenshot 8 Description")


