package peaksoft.restoranprojectjava14.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import peaksoft.restoranprojectjava14.entity.MenuItem;

import java.util.List;

@Data
@NoArgsConstructor
public class ChequeResponse {
    private Long id;
    private String waiterFullName;
    private List<MenuItem> items;
    private int priceAverage;
    private int service;
    private int grandTotal;
    @Builder
    public ChequeResponse(Long id, String waiterFullName, List<MenuItem> items, int priceAverage, int service, int grandTotal) {
        this.id = id;
        this.waiterFullName = waiterFullName;
        this.items = items;
        this.priceAverage = priceAverage;
        this.service = service;
        this.grandTotal = grandTotal;
    }
}
