package peaksoft.restoranprojectjava14.service;

import peaksoft.restoranprojectjava14.dto.request.SubCategoryRequest;
import peaksoft.restoranprojectjava14.dto.response.SimpleResponse;
import peaksoft.restoranprojectjava14.dto.response.SubCategoryResponse;
import peaksoft.restoranprojectjava14.dto.response.pagination.PaginationResponseSubCategory;

public interface SubCategoryService {
    PaginationResponseSubCategory findAll(Long id, int pageSize, int currentPage);
    SimpleResponse saveSubcategory(SubCategoryRequest subCategoryRequest);
    SubCategoryResponse findById(Long id);
    SimpleResponse update(Long id, SubCategoryRequest subCategoryRequest);
    SimpleResponse deleteById(Long id);
}
