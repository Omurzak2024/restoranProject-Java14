package peaksoft.restoranprojectjava14.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.restoranprojectjava14.dto.request.RestaurantRequest;
import peaksoft.restoranprojectjava14.dto.response.RestaurantResponse;
import peaksoft.restoranprojectjava14.dto.response.SimpleResponse;
import peaksoft.restoranprojectjava14.entity.Restaurant;
import peaksoft.restoranprojectjava14.entity.User;
import peaksoft.restoranprojectjava14.exceptions.AlreadyExistException;
import peaksoft.restoranprojectjava14.exceptions.NotFoundException;
import peaksoft.restoranprojectjava14.repository.RestaurantRepository;
import peaksoft.restoranprojectjava14.repository.UserRepository;
import peaksoft.restoranprojectjava14.service.RestaurantService;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Override
    public SimpleResponse saveRestaurant(RestaurantRequest restaurantRequest) {
        if (!restaurantRepository.findAll().isEmpty()){
            throw new AlreadyExistException("You mast save only one Restaurant");
        }
        if (restaurantRepository.existsByName(restaurantRequest.getName())){
           return SimpleResponse
                   .builder()
                   .message(String.format("Restaurant with name : %s already exists", restaurantRequest.getName()))
                   .build();
        }
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.getUserByEmail(email)
                .orElseThrow(
                        ()-> new NotFoundException("User with email : %s not found".formatted(email))
                );
        Restaurant restaurant = Restaurant
                .builder()
                .name(restaurantRequest.getName())
                .location(restaurantRequest.getLocation())
                .restType(restaurantRequest.getRestType())
                .service(restaurantRequest.getService())
                .build();
        restaurantRepository.save(restaurant);
        user.setRestaurant(restaurant);
        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Restaurant with name : %s saved successfully", restaurantRequest.getName()))
                .build();
    }

    @Override
    public RestaurantResponse findById(Long id) {
        return restaurantRepository.getRestaurantById(id).orElseThrow(
                ()-> new NotFoundException("Restaurant with id : %s not found".formatted(id))
        );
    }

    @Override
    public SimpleResponse update(Long id, RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Restaurant with id : %s not found".formatted(id))
        );
        restaurant.setName(restaurantRequest.getName());
        restaurant.setLocation(restaurantRequest.getLocation());
        restaurant.setService(restaurantRequest.getService());
        restaurant.setRestType(restaurantRequest.getRestType());
        restaurantRepository.save(restaurant);
        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Restaurant with id : " + id + "updated successfully"))
                .build();
    }

    @Override
    public SimpleResponse deleteById(Long id) {
        if (!restaurantRepository.existsById(id)){
            throw new NotFoundException("Restaurant with id : %s not found".formatted(id));
        }
        restaurantRepository.deleteById(id);
        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Restaurant with id : %s deleted successfully", id))
                .build();
    }
}
