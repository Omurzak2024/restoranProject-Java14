package peaksoft.restoranprojectjava14.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import peaksoft.restoranprojectjava14.enums.RestType;

@Getter
@Setter
public class RestaurantRequest {
    @NotEmpty(message = "fill in the field")
    private String name;
    @NotEmpty(message = "fill in the field")
    private String location;
    private RestType restType;
    @NotNull
    private int service;

    public RestaurantRequest(String location, String name, RestType restType, int service) {
        this.location = location;
        this.name = name;
        this.restType = restType;
        this.service = service;
    }

    public RestaurantRequest() {

    }
}
