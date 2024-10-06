package peaksoft.restoranprojectjava14.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {
    private Long id;
    private String categoryName;

    public CategoryResponse(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
}
