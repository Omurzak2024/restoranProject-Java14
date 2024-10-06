package peaksoft.restoranprojectjava14.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.restoranprojectjava14.dto.request.SignInRequest;
import peaksoft.restoranprojectjava14.dto.request.SignUpRequest;
import peaksoft.restoranprojectjava14.dto.response.AuthenticationResponse;
import peaksoft.restoranprojectjava14.service.AuthenticationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationApi {

    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public AuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
       return authenticationService.signUp(request);
    }

    @PostMapping("/signIn")
    public AuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }
}
