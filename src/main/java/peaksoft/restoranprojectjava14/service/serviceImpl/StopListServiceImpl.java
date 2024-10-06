package peaksoft.restoranprojectjava14.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.restoranprojectjava14.dto.request.StopListRequest;
import peaksoft.restoranprojectjava14.dto.response.SimpleResponse;
import peaksoft.restoranprojectjava14.dto.response.StopListResponse;
import peaksoft.restoranprojectjava14.dto.response.pagination.PaginationResponseStopList;
import peaksoft.restoranprojectjava14.entity.MenuItem;
import peaksoft.restoranprojectjava14.entity.StopList;
import peaksoft.restoranprojectjava14.exceptions.BadRequestException;
import peaksoft.restoranprojectjava14.exceptions.NotFoundException;
import peaksoft.restoranprojectjava14.repository.MenuItemRepository;
import peaksoft.restoranprojectjava14.repository.StopListRepository;
import peaksoft.restoranprojectjava14.service.StopListService;

import java.time.LocalDate;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class StopListServiceImpl implements StopListService {
    private final StopListRepository stopListRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public PaginationResponseStopList findAll(int pageSize, int currentPage) {
        Pageable pageable = PageRequest.of(currentPage-1, pageSize);
        Page<StopListResponse> allStopList = stopListRepository.findAllStopLists(pageable);
        return PaginationResponseStopList
                .builder()
                .userResponseList(allStopList.getContent())
                .page(allStopList.getNumber()+1)
                .size(allStopList.getTotalPages())
                .build();
    }

    @Override
    public StopListResponse saveStopList(Long menuItemId, StopListRequest stopListRequest) {
        int date1 = stopListRequest.getDate().compareTo(LocalDate.now());
        if (date1>=0){
            MenuItem menuItem = menuItemRepository.findById(menuItemId).orElseThrow(
                    ()-> new NotFoundException(String.format("MenuItem with id %s not found", menuItemId))
            );
            for (StopList stopList:stopListRepository.findAll()){
                int date = stopList.getDate().compareTo(stopList.getDate());
                if (date == 0){
                    if (Objects.equals(stopList.getMenuItem().getId(), menuItem.getId())){
                        throw new BadRequestException("Save Date Exception!");
                    }
                }
            }
            StopList stopList = new StopList(
                    stopListRequest.getReason(),
                    stopListRequest.getDate()
            );
            menuItem.setIsBlocked(stopList.getDate());
            stopList.setMenuItem(menuItem);
            stopListRepository.save(stopList);
            return new StopListResponse(
                    stopList.getId(),
                    stopList.getReason(),
                    stopList.getMenuItem().getName(),
                    stopList.getDate()
            );
        } else {
            throw new BadRequestException("date exception!");
        }
    }

    @Override
    public StopList findById(Long id) {
        return stopListRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Stop list with id " + id + " not found")
        );
    }

    @Override
    public SimpleResponse update(Long id, StopListRequest stopListRequest) {
        StopList stopList = stopListRepository.findById(id).orElseThrow(
                () -> new NotFoundException("StopList with id " + id + " not found")
        );
        stopList.setReason(stopListRequest.getReason());
        stopList.setDate(stopListRequest.getDate());
        stopListRepository.save(stopList);
        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("StopList with MenuItemName : %s" + "successfully update",
                        stopListRequest.getMenuItemName()))
                .build();
    }

    @Override
    public SimpleResponse deleteById(Long id) {
        if (!stopListRepository.existsById(id)) {
            throw new NotFoundException("StopList with id " + id + " not found");
        }
        stopListRepository.deleteById(id);
        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("StopList with id %s successfully deleted", id))
                .build();
    }
}
