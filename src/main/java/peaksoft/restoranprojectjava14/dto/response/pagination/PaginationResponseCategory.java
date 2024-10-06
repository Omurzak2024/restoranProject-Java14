package peaksoft.restoranprojectjava14.dto.response.pagination;

import lombok.Builder;
import peaksoft.restoranprojectjava14.dto.response.CategoryResponse;

import java.util.List;

@Builder
public record PaginationResponseCategory(
        List<CategoryResponse> categoryResponseList,
        int size,
        int page
) {
}
