package peaksoft.restoranprojectjava14.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.restoranprojectjava14.dto.response.RestaurantResponse;
import peaksoft.restoranprojectjava14.entity.Restaurant;

import java.util.Optional;


public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query("select  new  peaksoft.restoranprojectjava14.dto.response.RestaurantResponse(r.id,r.name,r.location,r.restType,r.numberOfEmployees,r.service) from Restaurant r where r.id=:id")
    Optional<RestaurantResponse> getRestaurantById(Long id);
    Optional<Restaurant> findRestaurantByName(String name);

    boolean existsByName(String name);
}
