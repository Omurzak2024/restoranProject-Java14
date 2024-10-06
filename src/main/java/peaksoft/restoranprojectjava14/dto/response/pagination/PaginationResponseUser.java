package peaksoft.restoranprojectjava14.dto.response.pagination;

import lombok.Builder;
import peaksoft.restoranprojectjava14.dto.response.UserResponse;

import java.util.List;
@Builder
public record PaginationResponseUser(
        List<UserResponse> userResponseList,
        int size,
        int page
) {
}
