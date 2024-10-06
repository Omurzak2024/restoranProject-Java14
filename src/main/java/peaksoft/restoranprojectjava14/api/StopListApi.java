package peaksoft.restoranprojectjava14.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.restoranprojectjava14.dto.request.StopListRequest;
import peaksoft.restoranprojectjava14.dto.response.SimpleResponse;
import peaksoft.restoranprojectjava14.dto.response.StopListResponse;
import peaksoft.restoranprojectjava14.dto.response.pagination.PaginationResponseStopList;
import peaksoft.restoranprojectjava14.service.StopListService;

@RestController
@RequestMapping("/stopLists")
@RequiredArgsConstructor
public class StopListApi {
    private final StopListService stopListService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public PaginationResponseStopList findAll(int pageSize, int currentPage) {
        return stopListService.findAll(pageSize, currentPage);
    }

    @PostMapping("/menuId")
    @PreAuthorize("hasAuthority('ADMIN')")
    public StopListResponse saveStopList(@PathVariable Long menuId, @RequestBody StopListRequest stopListRequest) {
        return stopListService.saveStopList(menuId, stopListRequest);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public StopListResponse findById(@PathVariable Long id) {
        return findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse updateStopListById(@PathVariable Long id, @RequestBody StopListRequest stopListRequest) {
        return stopListService.update(id,stopListRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse deleteStopListById(@PathVariable Long id) {
        return stopListService.deleteById(id);
    }
}
