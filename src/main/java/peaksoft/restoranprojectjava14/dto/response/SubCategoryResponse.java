package peaksoft.restoranprojectjava14.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubCategoryResponse {
    private Long id;
    private String name;

    public SubCategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
