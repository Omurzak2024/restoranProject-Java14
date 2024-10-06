package peaksoft.restoranprojectjava14.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MenuItemResponse {
    private Long id;
    private String name;
    private String image;
    private int price;
    private String description;
    private boolean isVegetarian;

    public MenuItemResponse(Long id, String name, String image, int price, String description, boolean isVegetarian) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.isVegetarian = isVegetarian;
    }
}
