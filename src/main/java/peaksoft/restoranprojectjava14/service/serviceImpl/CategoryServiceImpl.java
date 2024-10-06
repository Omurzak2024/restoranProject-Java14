package peaksoft.restoranprojectjava14.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.restoranprojectjava14.dto.request.CategoryRequest;
import peaksoft.restoranprojectjava14.dto.response.CategoryResponse;
import peaksoft.restoranprojectjava14.dto.response.SimpleResponse;
import peaksoft.restoranprojectjava14.dto.response.pagination.PaginationResponseCategory;
import peaksoft.restoranprojectjava14.entity.Category;
import peaksoft.restoranprojectjava14.exceptions.NotFoundException;
import peaksoft.restoranprojectjava14.repository.CategoryRepository;
import peaksoft.restoranprojectjava14.service.CategoryService;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public PaginationResponseCategory findAll(int pageSize, int currentPage) {
        Pageable pageable = PageRequest.of(currentPage-1, pageSize);
        Page<CategoryResponse> allCategory=categoryRepository.getAllCategories(pageable);
        return PaginationResponseCategory
                .builder()
                .categoryResponseList(allCategory.getContent())
                .page(allCategory.getNumber()+1)
                .size(allCategory.getTotalPages())
                .build();
    }

    @Override
    public SimpleResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setCategoryName(categoryRequest.getName());
        categoryRepository.save(category);
        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message("Category with name : %s" +
                 "successfully saved")
                .build();
    }

    @Override
    public CategoryResponse findById(Long id) {
        return categoryRepository.getCategoriesById(id).orElseThrow(
                ()-> new NotFoundException("Category with id : " +id+ " is not found!")
        );
    }

    @Override
    public SimpleResponse update(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Category with id : " +id+ " is not found!"));
                category.setCategoryName(categoryRequest.getName());
                categoryRepository.save(category);
        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Category with name : %s successfully update",
                        categoryRequest.getName()))
                .build();
    }

    @Override
    public SimpleResponse deleteById(Long id) {
        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Category with id : %s successfully deleted!",id))
                .build();
    }
}
