package peaksoft.restoranprojectjava14.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class AverageSumResponse {
    private int sum;
}
