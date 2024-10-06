package peaksoft.restoranprojectjava14.dto.response.pagination;

import peaksoft.restoranprojectjava14.dto.response.ChequeResponse;

import java.util.List;

public record PaginationResponseCheque(
        List<ChequeResponse> userResponseList,
        int size,
        int page
) {
}
