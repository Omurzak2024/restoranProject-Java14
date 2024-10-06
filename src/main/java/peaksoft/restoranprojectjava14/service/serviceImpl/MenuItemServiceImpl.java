package peaksoft.restoranprojectjava14.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.restoranprojectjava14.dto.request.MenuItemRequest;
import peaksoft.restoranprojectjava14.dto.response.MenuItemResponse;
import peaksoft.restoranprojectjava14.dto.response.SearchResponse;
import peaksoft.restoranprojectjava14.dto.response.SimpleResponse;
import peaksoft.restoranprojectjava14.dto.response.pagination.PaginationResponseMenuItem;
import peaksoft.restoranprojectjava14.entity.MenuItem;
import peaksoft.restoranprojectjava14.entity.Restaurant;
import peaksoft.restoranprojectjava14.entity.SubCategory;
import peaksoft.restoranprojectjava14.exceptions.NotFoundException;
import peaksoft.restoranprojectjava14.repository.MenuItemRepository;
import peaksoft.restoranprojectjava14.repository.RestaurantRepository;
import peaksoft.restoranprojectjava14.repository.SubCategoryRepository;
import peaksoft.restoranprojectjava14.service.MenuItemService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public PaginationResponseMenuItem findAllMenuItems(int pageSize, int currentPage) {
        Pageable pageable = PageRequest.of(currentPage-1, pageSize);
        Page<MenuItemResponse> allMenuItem = menuItemRepository.findAllMenuItems(pageable);
        return PaginationResponseMenuItem
                .builder()
                .menuItemList(allMenuItem.getContent())
                .page(allMenuItem.getNumber()+1)
                .size(allMenuItem.getTotalPages())
                .build();
    }

    @Override
    public SimpleResponse saveMenuItem(MenuItemRequest menuItemRequest) {
        Restaurant restaurant = restaurantRepository.findById(menuItemRequest.getRestaurantId()).orElseThrow(
                ()-> new NotFoundException("Restaurant with id" + menuItemRequest.getRestaurantId() + "is not found!"));
        SubCategory subCategory = subCategoryRepository.findById(menuItemRequest.getSubCategoryId()).orElseThrow(
                () -> new NotFoundException("SubCategory with id" + menuItemRequest.getSubCategoryId() + "is not found!"));

        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemRequest.getName());
        menuItem.setImage(menuItemRequest.getImage());
        menuItem.setDescription(menuItemRequest.getDescription());
        menuItem.setPrice(menuItemRequest.getPrice());
        menuItem.setRestaurant(restaurant);
        menuItem.setSubCategory(subCategory);
        menuItem.setVegetarian(menuItemRequest.isVegetarian());

        subCategory.addMenuItem(menuItem);
        restaurant.addMenuItem(menuItem);
        menuItemRepository.save(menuItem);

        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("MenuItem with name: %s saved successfully!", menuItemRequest.getName()))
                .build();
    }

    @Override
    public MenuItemResponse findById(Long id) {
        return menuItemRepository.getMenuItemById(id).orElseThrow(
                () -> new NotFoundException("MenuItem with id" + id + "is not found!"));
    }

    @Override
    public SimpleResponse update(Long id, MenuItemRequest menuItemRequest) {
        MenuItem menuItem1 = menuItemRepository.findById(id).orElseThrow(
                () -> new NotFoundException("MenuItem with id" + id + "is not found!"));
        menuItem1.setName(menuItemRequest.getName());
        menuItem1.setImage(menuItemRequest.getImage());
        menuItem1.setDescription(menuItemRequest.getDescription());
        menuItem1.setPrice(menuItemRequest.getPrice());
        menuItemRepository.save(menuItem1);
        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("MenuItem with name: %s updated successfully!", menuItemRequest.getName()))
                .build();
    }

    @Override
    public SimpleResponse deleteById(Long id) {
        if (!menuItemRepository.existsById(id)) {
            throw new NotFoundException("MenuItem with id" + id + "is not found!");
        }
        menuItemRepository.deleteById(id);
        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("MenuItem with id: %s deleted successfully!", id))
                .build();
    }

    @Override
    public List<SearchResponse> search(String keyword) {
        return menuItemRepository.search(keyword);
    }

    @Override
    public List<MenuItemResponse> findAllMenuItemSortedByPriceAscAndDesc(String sort) {
        if (sort.equalsIgnoreCase("asc")){
            return menuItemRepository.getAllByOrderByPriceAsc();
        }else if (sort.equalsIgnoreCase("desc")){
            return menuItemRepository.getAllByOrderByPriceDesc();
        }else
        return new ArrayList<>();
    }

    @Override
    public List<MenuItemResponse> filter(Boolean isVegetarian) {
        return menuItemRepository.filter(isVegetarian);
    }
}
