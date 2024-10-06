package peaksoft.restoranprojectjava14.dto.response;

import lombok.Builder;
import lombok.Data;
import peaksoft.restoranprojectjava14.enums.RestType;

@Data
@Builder
public class RestaurantResponse {
    private Long id;
    private String name;
    private String location;
    private RestType restType;
    private int numberOfEmployees;
    private int service;

    public RestaurantResponse(Long id, String name, String location, RestType restType, int numberOfEmployees, int service) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.restType = restType;
        this.numberOfEmployees = numberOfEmployees;
        this.service = service;
    }
}
