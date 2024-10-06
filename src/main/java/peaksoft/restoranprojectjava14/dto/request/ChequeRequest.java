package peaksoft.restoranprojectjava14.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ChequeRequest {
    private List<Long> menuItemNames;
    public ChequeRequest(){

    }
}
