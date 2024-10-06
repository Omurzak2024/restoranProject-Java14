package peaksoft.restoranprojectjava14.service;

import peaksoft.restoranprojectjava14.dto.request.SignInRequest;
import peaksoft.restoranprojectjava14.dto.request.SignUpRequest;
import peaksoft.restoranprojectjava14.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse signUp(SignUpRequest signUpRequest);
    AuthenticationResponse signIn(SignInRequest signInRequest);
}
