package peaksoft.restoranprojectjava14.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class StopListResponse {
    private Long id;
    private String reason;
    private String menuItemName;
    private LocalDate date;

    public StopListResponse(Long id, String reason, String menuItemName, LocalDate date) {
        this.id = id;
        this.reason = reason;
        this.menuItemName = menuItemName;
        this.date = date;
    }
}
