package peaksoft.restoranprojectjava14.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.restoranprojectjava14.enums.Role;
import peaksoft.restoranprojectjava14.validator.Password;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotEmpty(message = "fill in the field")
    private  String firstName;
    @NotEmpty(message = "fill in the field")
    private String lastName;
    private LocalDate dateOfBirth;
    @Email
    @NotEmpty(message = "fill in the field")
    private String email;
    @NotEmpty(message = "fill in the field")
    @Password(message = "Incorrect password")
    private String password;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role;
    private int experience;
}
