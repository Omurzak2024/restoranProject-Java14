package peaksoft.restoranprojectjava14.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.restoranprojectjava14.dto.response.SubCategoryResponse;
import peaksoft.restoranprojectjava14.entity.SubCategory;

import java.util.Optional;


public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    Page<SubCategoryResponse> findSubcategoriesByCategoryId(Long id, Pageable pageable);
    @Query("select new peaksoft.restoranprojectjava14.dto.response.SubCategoryResponse(s.id,s.name) from SubCategory s where s.id=:id")
    Optional<SubCategoryResponse> finId(Long id);
}
