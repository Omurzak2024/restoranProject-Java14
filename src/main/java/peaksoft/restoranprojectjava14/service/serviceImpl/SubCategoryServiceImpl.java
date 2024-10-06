package peaksoft.restoranprojectjava14.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.restoranprojectjava14.dto.request.SubCategoryRequest;
import peaksoft.restoranprojectjava14.dto.response.SimpleResponse;
import peaksoft.restoranprojectjava14.dto.response.SubCategoryResponse;
import peaksoft.restoranprojectjava14.dto.response.pagination.PaginationResponseSubCategory;
import peaksoft.restoranprojectjava14.entity.Category;
import peaksoft.restoranprojectjava14.entity.SubCategory;
import peaksoft.restoranprojectjava14.exceptions.NotFoundException;
import peaksoft.restoranprojectjava14.repository.CategoryRepository;
import peaksoft.restoranprojectjava14.repository.SubCategoryRepository;
import peaksoft.restoranprojectjava14.service.SubCategoryService;


@Service
@Transactional
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public PaginationResponseSubCategory findAll(Long id, int pageSize, int currentPage) {
        Pageable pageable = PageRequest.of(currentPage-1, pageSize);
        Page<SubCategoryResponse> allSubCategory = subCategoryRepository.findSubcategoriesByCategoryId(id, pageable);
        return PaginationResponseSubCategory
                .builder()
                .subCategoryResponseList(allSubCategory.getContent())
                .page(allSubCategory.getNumber()+1)
                .size(allSubCategory.getTotalPages())
                .build();

    }

    @Override
    public SimpleResponse saveSubcategory(SubCategoryRequest subCategoryRequest) {
        Category category = categoryRepository.findById(subCategoryRequest.getCategoryId()).orElseThrow(
                ()-> new NotFoundException("User with id " + subCategoryRequest.getCategoryId() + " not found")
        );
        SubCategory subCategory1 = new SubCategory();
        subCategory1.setName(subCategoryRequest.getName());
        category.addSubCategory(subCategory1);
        subCategory1.setCategory(category);
        subCategoryRepository.save(subCategory1);
        categoryRepository.save(category);
        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Subcategory with name : %s added successfully",
                        subCategoryRequest.getName()))
                .build();
    }

    @Override
    public SubCategoryResponse findById(Long id) {
        return subCategoryRepository.finId(id).orElseThrow(
                ()-> new NotFoundException("Subcategory with id " + id + " not found")
        );
    }


    @Override
    public SimpleResponse update(Long id, SubCategoryRequest subCategoryRequest) {
        SubCategory oldSubCategory = subCategoryRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Subcategory with id " + id + " not found")
        );
        Category category = categoryRepository.findById(subCategoryRequest.getCategoryId()).orElseThrow(
                ()-> new NotFoundException("Category with id " + subCategoryRequest.getCategoryId() + " not found")
        );
        oldSubCategory.setName(subCategoryRequest.getName());
        oldSubCategory.setCategory(category);
        subCategoryRepository.save(oldSubCategory);
        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("SubCategory with name : %s successfully update",
                        subCategoryRequest.getName()))
                .build();
    }

    @Override
    public SimpleResponse deleteById(Long id) {
        if (!subCategoryRepository.existsById(id)) {
            throw new NotFoundException("Subcategory with id " + id + " not found");
        }
        subCategoryRepository.deleteById(id);
        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Subcategory with id : %s is deleted!",id))
                .build();
    }
}
