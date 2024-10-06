package peaksoft.restoranprojectjava14.service.serviceImpl;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.restoranprojectjava14.config.JwtService;
import peaksoft.restoranprojectjava14.dto.request.SignInRequest;
import peaksoft.restoranprojectjava14.dto.request.SignUpRequest;
import peaksoft.restoranprojectjava14.dto.response.AuthenticationResponse;
import peaksoft.restoranprojectjava14.entity.User;
import peaksoft.restoranprojectjava14.enums.Role;
import peaksoft.restoranprojectjava14.exceptions.BadCredentialException;
import peaksoft.restoranprojectjava14.exceptions.NoSuchElementException;
import peaksoft.restoranprojectjava14.repository.UserRepository;
import peaksoft.restoranprojectjava14.service.AuthenticationService;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EntityExistsException(String.format(
                    "User with email %s already exists", signUpRequest.getEmail()
            ));
        }

        User user = User.builder()
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(signUpRequest.getRole())
                .build();
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
       User user = userRepository.getUserByEmail(signInRequest.getEmail())
               .orElseThrow(()-> new NoSuchElementException(
                       "User with email " + signInRequest.getEmail() + " does not exist"
               ));
       if (signInRequest.getEmail().isBlank()){
           throw new BadCredentialException("Incorrect password!");
       }

       if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
           throw new BadCredentialException("Incorrect password!");
       }

       String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @PostConstruct
    public void initAdmin(){
        String email = "admin@peaksoft.com";
        User user = User.builder()
                .firstName("Admin")
                .lastName("Peaksoft")
                .dateOfBirth(LocalDate.of(2002,05,10))
                .email("admin@peaksoft.com")
                .password(passwordEncoder.encode("admin"))
                .role(Role.ADMIN)
                .phoneNumber("+996708021311")
                .experience(5)
                .build();

        if (!userRepository.existsByEmail(user.getEmail())) {
            userRepository.save(user);
        }
    }
}
