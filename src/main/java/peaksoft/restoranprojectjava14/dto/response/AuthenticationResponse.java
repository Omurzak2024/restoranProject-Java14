package peaksoft.restoranprojectjava14.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.restoranprojectjava14.enums.Role;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AuthenticationResponse {
    private String token;
    private String email;
    private Role role;
}
