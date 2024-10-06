package peaksoft.restoranprojectjava14.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SearchResponse {
    private String categoryName;
    private String subCategoryName;
    private String menuItemName;
    private String image;
    private int price;

    public SearchResponse(String categoryName, String subCategoryName, String menuItemName, String image, int price) {
        this.categoryName = categoryName;
        this.subCategoryName = subCategoryName;
        this.menuItemName = menuItemName;
        this.image = image;
        this.price = price;
    }

    public SearchResponse(String menuItemName, String image, int price) {
        this.menuItemName = menuItemName;
        this.image = image;
        this.price = price;
    }
}
