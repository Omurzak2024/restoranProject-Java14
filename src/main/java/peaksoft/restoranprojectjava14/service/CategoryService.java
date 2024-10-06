package peaksoft.restoranprojectjava14.service;

import peaksoft.restoranprojectjava14.dto.request.CategoryRequest;
import peaksoft.restoranprojectjava14.dto.response.CategoryResponse;
import peaksoft.restoranprojectjava14.dto.response.SimpleResponse;
import peaksoft.restoranprojectjava14.dto.response.pagination.PaginationResponseCategory;

public interface CategoryService {
    PaginationResponseCategory findAll(int pageSize, int currentPage);
    SimpleResponse saveCategory(CategoryRequest categoryRequest);
    CategoryResponse findById(Long id);
    SimpleResponse update(Long id, CategoryRequest categoryRequest);
    SimpleResponse deleteById(Long id);
}
