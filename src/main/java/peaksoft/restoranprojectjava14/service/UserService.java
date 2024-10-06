package peaksoft.restoranprojectjava14.service;

import peaksoft.restoranprojectjava14.dto.request.UserRequest;
import peaksoft.restoranprojectjava14.dto.response.SimpleResponse;
import peaksoft.restoranprojectjava14.dto.response.UserResponse;
import peaksoft.restoranprojectjava14.dto.response.pagination.PaginationResponseUser;

public interface UserService {
    PaginationResponseUser getAllUser(int pageSize, int currentPage);
    SimpleResponse register(UserRequest userRequest);
    SimpleResponse acceptUser(Long restaurantId, Long userId, String word);
    SimpleResponse updateUserById(Long id, UserRequest userRequest);
    SimpleResponse deleteUserById(Long id);
    UserResponse getUserById(Long id);
    SimpleResponse saveUser(Long restaurantId, UserRequest userRequest);
}
