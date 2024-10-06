package peaksoft.restoranprojectjava14.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantRequestOfDay {
    private Long id;
    private LocalDate day;
}
