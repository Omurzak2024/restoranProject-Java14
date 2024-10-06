package peaksoft.restoranprojectjava14.dto.response.pagination;

import lombok.Builder;
import peaksoft.restoranprojectjava14.dto.response.MenuItemResponse;

import java.util.List;
@Builder
public record PaginationResponseMenuItem(
        List<MenuItemResponse> menuItemList,
        int size,
        int page
) {
}
