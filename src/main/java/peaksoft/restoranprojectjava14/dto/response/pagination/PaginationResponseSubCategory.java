package peaksoft.restoranprojectjava14.dto.response.pagination;

import lombok.Builder;
import peaksoft.restoranprojectjava14.dto.response.SubCategoryResponse;

import java.util.List;
@Builder
public record PaginationResponseSubCategory(
        List<SubCategoryResponse> subCategoryResponseList,
        int size,
        int page
) {
}
