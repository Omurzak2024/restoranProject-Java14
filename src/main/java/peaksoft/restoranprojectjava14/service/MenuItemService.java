package peaksoft.restoranprojectjava14.service;

import org.springframework.data.repository.query.Param;
import peaksoft.restoranprojectjava14.dto.request.MenuItemRequest;
import peaksoft.restoranprojectjava14.dto.response.MenuItemResponse;
import peaksoft.restoranprojectjava14.dto.response.SearchResponse;
import peaksoft.restoranprojectjava14.dto.response.SimpleResponse;
import peaksoft.restoranprojectjava14.dto.response.pagination.PaginationResponseMenuItem;
import peaksoft.restoranprojectjava14.entity.MenuItem;

import java.util.List;

public interface MenuItemService {
    PaginationResponseMenuItem findAllMenuItems(int pageSize, int currentPage);
    SimpleResponse saveMenuItem(MenuItemRequest menuItemRequest);
    MenuItemResponse findById(Long id);
    SimpleResponse update(Long id, MenuItemRequest menuItemRequest);
    SimpleResponse deleteById(Long id);
    List<SearchResponse> search(String keyword);
    List<MenuItemResponse> findAllMenuItemSortedByPriceAscAndDesc(String sort);
    List<MenuItemResponse> filter(@Param("isVegetarian") Boolean isVegetarian);
}
