package peaksoft.restoranprojectjava14.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubCategoryRequest {
    private Long categoryId;
    @NotEmpty(message = "fill in the field")
    private String name;
}
