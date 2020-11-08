package sk.gw.jo2o.petshop.auth.service;

import static java.lang.Boolean.TRUE;
import static sk.gw.jo2o.petshop.auth.model.Role.USER;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.auth.model.JwtData;
import sk.gw.jo2o.petshop.auth.model.RegistrationData;
import sk.gw.jo2o.petshop.entity.Credential;
import sk.gw.jo2o.petshop.entity.User;
import sk.gw.jo2o.petshop.exception.PetShopAuthException;
import sk.gw.jo2o.petshop.repo.CredentialRepository;
import sk.gw.jo2o.petshop.repo.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final CredentialRepository credentialRepository;

    public JwtData login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return JwtData.builder()
                .token(jwtService.generate(authentication))
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .roles(List.of(USER.name()))
                .build();
    }

    public String register(RegistrationData registrationData) {
        if (TRUE.equals(userRepository.existsByUsername(registrationData.getUsername()))) {
            throw new PetShopAuthException("Username already taken!");
        }
        if (TRUE.equals(userRepository.existsByEmail(registrationData.getEmail()))) {
            throw new PetShopAuthException("Email already taken!");
        }

        Credential credential = credentialRepository.save(Credential.builder()
                .password(passwordEncoder.encode(registrationData.getPassword()))
                .roles(USER.name())
                .build());

        userRepository.save(User.builder()
                .username(registrationData.getUsername())
                .email(registrationData.getEmail())
                .credential(credential)
                .build());

        return "User registered successfully!";
    }

}
