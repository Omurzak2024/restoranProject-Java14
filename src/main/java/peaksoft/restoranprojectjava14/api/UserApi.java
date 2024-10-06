package peaksoft.restoranprojectjava14.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.restoranprojectjava14.dto.request.UserRequest;
import peaksoft.restoranprojectjava14.dto.response.SimpleResponse;
import peaksoft.restoranprojectjava14.dto.response.UserResponse;
import peaksoft.restoranprojectjava14.dto.response.pagination.PaginationResponseUser;
import peaksoft.restoranprojectjava14.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF','WAITER')")
    PaginationResponseUser getAllUsers(@RequestParam int pageSize, int currentPage) {
        return userService.getAllUser(pageSize, currentPage);
    }

    @PostMapping("/register")
    public SimpleResponse registerUser(@RequestBody @Valid UserRequest userRequest){
        return userService.register(userRequest);
    }

    @PutMapping("/acceptorReject")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse acceptorReject(@RequestParam Long userId, @RequestParam Long restaurantId, @RequestParam String word) {
        return userService.acceptUser(userId, restaurantId, word);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return userService.updateUserById(id, userRequest);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER','CHEF')")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER','CHEF')")
    public SimpleResponse deleteUserById(@PathVariable Long id) {
        return userService.deleteUserById(id);
    }

    @PostMapping("/saveByAdmin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse saveUserByAdmin(@RequestParam Long id, @RequestBody @Valid UserRequest userRequest) {
        return userService.saveUser(id, userRequest);
    }
}
