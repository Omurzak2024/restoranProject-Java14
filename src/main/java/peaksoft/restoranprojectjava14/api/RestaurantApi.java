package peaksoft.restoranprojectjava14.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.restoranprojectjava14.dto.request.RestaurantRequest;
import peaksoft.restoranprojectjava14.dto.response.RestaurantResponse;
import peaksoft.restoranprojectjava14.dto.response.SimpleResponse;
import peaksoft.restoranprojectjava14.service.RestaurantService;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantApi {
    private final RestaurantService restaurantService;

    @PostMapping
    @Operation(summary = "save",description = "saveRestaurant")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse saveRestaurant(@RequestBody RestaurantRequest restaurantRequest){
        return restaurantService.saveRestaurant(restaurantRequest);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public RestaurantResponse findById(@PathVariable Long id){
        return restaurantService.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse updateRestaurantById(@PathVariable Long id, @RequestBody RestaurantRequest restaurantRequest){
        return restaurantService.update(id,restaurantRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse deleteRestaurantById(@PathVariable Long id){
        return restaurantService.deleteById(id);
    }
}
