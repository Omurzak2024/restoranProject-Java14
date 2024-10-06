package peaksoft.restoranprojectjava14.dto.response.pagination;

import lombok.Builder;
import peaksoft.restoranprojectjava14.dto.response.StopListResponse;

import java.util.List;
@Builder
public record PaginationResponseStopList(
        List<StopListResponse> userResponseList,
        int size,
        int page
) {
}
