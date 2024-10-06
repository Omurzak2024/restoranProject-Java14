package peaksoft.restoranprojectjava14.service;

import peaksoft.restoranprojectjava14.dto.request.RestaurantRequest;
import peaksoft.restoranprojectjava14.dto.response.RestaurantResponse;
import peaksoft.restoranprojectjava14.dto.response.SimpleResponse;

public interface RestaurantService {
    SimpleResponse saveRestaurant(RestaurantRequest restaurantRequest);
    RestaurantResponse findById(Long id);
    SimpleResponse update(Long id, RestaurantRequest restaurantRequest);
    SimpleResponse deleteById(Long id);
}
