package peaksoft.restoranprojectjava14.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class MenuItemRequest {
    @NotEmpty(message = "fill in the field")
    private String name;
    @NotEmpty(message = "fill in the field")
    private String image;
    @NotEmpty(message = "fill in the field")
    @PositiveOrZero
    private int price;
    @NotEmpty(message = "fill in the field")
    private String description;
    private boolean isVegetarian;
    @NotEmpty(message = "fill in the field")
    private Long restaurantId;
    @NotEmpty(message = "fill in the field")
    private Long subCategoryId;
}
