package peaksoft.restoranprojectjava14.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.restoranprojectjava14.dto.response.MenuItemResponse;
import peaksoft.restoranprojectjava14.dto.response.SearchResponse;
import peaksoft.restoranprojectjava14.entity.MenuItem;

import java.util.List;
import java.util.Optional;


public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    @Query("select new peaksoft.restoranprojectjava14.dto.response.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian) from MenuItem m")
    Page<MenuItemResponse> findAllMenuItems(Pageable pageable);
    MenuItem findByName(String name);

    Optional<MenuItemResponse> getMenuItemById(Long id);

    @Query("SELECT NEW peaksoft.restoranprojectjava14.dto.response.SearchResponse(m.name, m.image, m.price) FROM MenuItem m " +
            "WHERE m.name ILIKE '%' || ?1 || '%'")
    List<SearchResponse> search(String keyWord);

    List<MenuItemResponse> getAllByOrderByPriceAsc();

    List<MenuItemResponse> getAllByOrderByPriceDesc();

    Optional<MenuItem> findMenuItemByName(String menuItemName);

    @Query("SELECT NEW peaksoft.restoranprojectjava14.dto.response.MenuItemResponse(m.id, m.name, m.image, m.price, m.description, m.isVegetarian) " +
            "FROM MenuItem m " +
            "WHERE m.isVegetarian = :isVegetarian")
    List<MenuItemResponse> filter(@Param("isVegetarian") Boolean isVegetarian);

    @Query("select new peaksoft.restoranprojectjava14.dto.response.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian) from MenuItem m where m.name=?1")
    MenuItemResponse findByNameResponse(String name);
}
