package peaksoft.restoranprojectjava14.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequest {
    @NotEmpty(message = "fill in the field")
    private String email;
    @NotEmpty(message = "fill in the field")
    private String password;
}
