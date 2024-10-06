package peaksoft.restoranprojectjava14.dto.request;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.restoranprojectjava14.enums.Role;
import peaksoft.restoranprojectjava14.validator.Password;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String email;
    @Password(message = "incorrect password up")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
