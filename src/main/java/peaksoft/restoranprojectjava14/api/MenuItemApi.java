package peaksoft.restoranprojectjava14.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.restoranprojectjava14.dto.request.MenuItemRequest;
import peaksoft.restoranprojectjava14.dto.response.MenuItemResponse;
import peaksoft.restoranprojectjava14.dto.response.SearchResponse;
import peaksoft.restoranprojectjava14.dto.response.SimpleResponse;
import peaksoft.restoranprojectjava14.dto.response.pagination.PaginationResponseMenuItem;
import peaksoft.restoranprojectjava14.service.MenuItemService;

import java.util.List;

@RestController
@RequestMapping("/menuItems")
@RequiredArgsConstructor
public class MenuItemApi {

    private final MenuItemService menuItemService;

    @GetMapping
    public PaginationResponseMenuItem findAllItems(int pageSize, int currentPage) {
        return menuItemService.findAllMenuItems(pageSize, currentPage);
    }

    @PostMapping
    public SimpleResponse save(@RequestBody MenuItemRequest menuItemRequest) {
        return menuItemService.saveMenuItem(menuItemRequest);
    }

    @GetMapping("/{id}")
    public MenuItemResponse findById(@PathVariable Long id) {
        return menuItemService.findById(id);
    }

    @PutMapping("/{id}")
    public SimpleResponse update(@PathVariable Long id, @RequestBody MenuItemRequest menuItemRequest) {
        return menuItemService.update(id, menuItemRequest);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id) {
        return menuItemService.deleteById(id);
    }

    @GetMapping("/search")
    public List<SearchResponse> search(@RequestParam String keyWord){
        return menuItemService.search(keyWord);
    }

    @GetMapping("/sort")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<MenuItemResponse> findAllMenuItemSortedByPriceAscAndDesc(@RequestParam String sort){
        return menuItemService.findAllMenuItemSortedByPriceAscAndDesc(sort);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @GetMapping("/filter")
    List<MenuItemResponse> filter(@RequestParam(required = false) Boolean isVegetarian){
        return menuItemService.filter(isVegetarian);
    }
}
