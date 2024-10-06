package peaksoft.restoranprojectjava14.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.restoranprojectjava14.dto.request.SubCategoryRequest;
import peaksoft.restoranprojectjava14.dto.response.SimpleResponse;
import peaksoft.restoranprojectjava14.dto.response.SubCategoryResponse;
import peaksoft.restoranprojectjava14.dto.response.pagination.PaginationResponseSubCategory;
import peaksoft.restoranprojectjava14.service.SubCategoryService;

@RestController
@RequestMapping("/subcategories")
@RequiredArgsConstructor
public class SubCategoryApi {
    private final SubCategoryService subCategoryService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public PaginationResponseSubCategory findAll(@RequestParam Long id, int pageSize, int currentPage) {
        return subCategoryService.findAll(id, pageSize, currentPage);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse save(@RequestBody SubCategoryRequest subCategoryRequest){
        return subCategoryService.saveSubcategory(subCategoryRequest);
    }

    @GetMapping("/sub/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SubCategoryResponse findById(@PathVariable Long id){
        return subCategoryService.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse update(@PathVariable Long id, @RequestBody SubCategoryRequest subCategoryRequest){
        return subCategoryService.update(id, subCategoryRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse delete(@PathVariable Long id){
        return subCategoryService.deleteById(id);
    }
}
