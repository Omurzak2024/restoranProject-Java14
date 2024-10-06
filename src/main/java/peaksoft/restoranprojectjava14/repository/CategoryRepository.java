package peaksoft.restoranprojectjava14.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.restoranprojectjava14.dto.response.CategoryResponse;
import peaksoft.restoranprojectjava14.entity.Category;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {


    @Query("select new peaksoft.restoranprojectjava14.dto.response.CategoryResponse(c.id,c.categoryName) from Category c ")
    Page<CategoryResponse> getAllCategories(Pageable pageable);

    Optional<CategoryResponse> getCategoriesById(Long id);
    boolean existsCategoriesByName(String name);
}
