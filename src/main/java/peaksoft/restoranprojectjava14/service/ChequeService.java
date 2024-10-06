package peaksoft.restoranprojectjava14.service;

import peaksoft.restoranprojectjava14.dto.request.ChequeRequest;
import peaksoft.restoranprojectjava14.dto.response.AverageSumResponse;
import peaksoft.restoranprojectjava14.dto.response.ChequeTotalWaiterResponse;
import peaksoft.restoranprojectjava14.dto.response.SimpleResponse;
import peaksoft.restoranprojectjava14.dto.response.pagination.PaginationResponseCheque;

import java.time.LocalDate;

public interface ChequeService {
    PaginationResponseCheque getAllCheques();
    SimpleResponse saveCheque(Long userId, ChequeRequest chequeRequest);
    SimpleResponse updateCheque(Long id, ChequeRequest chequeRequest);
    ChequeTotalWaiterResponse chequeTotalByWaiter(Long waiterId, LocalDate date);
    SimpleResponse deleteCheque(Long id);
    AverageSumResponse getAverageSum(LocalDate date);
    AverageSumResponse getAverageSumOfWaiter(Long waiterId, LocalDate dateTime);
}
