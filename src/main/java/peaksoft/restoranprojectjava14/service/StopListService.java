package peaksoft.restoranprojectjava14.service;

import peaksoft.restoranprojectjava14.dto.request.StopListRequest;
import peaksoft.restoranprojectjava14.dto.response.SimpleResponse;
import peaksoft.restoranprojectjava14.dto.response.StopListResponse;
import peaksoft.restoranprojectjava14.dto.response.pagination.PaginationResponseStopList;
import peaksoft.restoranprojectjava14.entity.StopList;

public interface StopListService {
    PaginationResponseStopList findAll(int pageSize, int currentPage);
    StopListResponse saveStopList(Long menuId, StopListRequest stopListRequest);
    StopList findById(Long id);
    SimpleResponse update(Long id, StopListRequest stopListRequest);
    SimpleResponse deleteById(Long id);
}
