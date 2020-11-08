package sk.gw.jo2o.petshop.auth.service;

import static java.util.stream.Collectors.toList;
import static sk.gw.jo2o.petshop.auth.model.enums.Role.USER;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.auth.model.JwtData;
import sk.gw.jo2o.petshop.auth.model.RegistrationData;
import sk.gw.jo2o.petshop.entity.Credential;
import sk.gw.jo2o.petshop.entity.User;
import sk.gw.jo2o.petshop.exception.PetShopAuthException;
import sk.gw.jo2o.petshop.repo.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtData login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return JwtData.builder()
                .token(jwtService.generate(authentication))
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .roles(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(toList()))
                .build();
    }

    public String register(RegistrationData registrationData) {
        if (userRepository.existsByUsername(registrationData.getUsername())) {
            throw new PetShopAuthException("Username already taken!");
        }
        if (userRepository.existsByEmail(registrationData.getEmail())) {
            throw new PetShopAuthException("Email already taken!");
        }

        userRepository.save(User.builder()
                .username(registrationData.getUsername())
                .email(registrationData.getEmail())
                .credential(Credential.builder()
                        .password(passwordEncoder.encode(registrationData.getPassword()))
                        .roles(USER.name())
                        .build())
                .build());

        return "User registered successfully!";
    }

}
