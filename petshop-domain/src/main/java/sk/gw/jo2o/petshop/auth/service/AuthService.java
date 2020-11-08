package sk.gw.jo2o.petshop.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.auth.model.JwtData;
import sk.gw.jo2o.petshop.auth.model.RegistrationData;
import sk.gw.jo2o.petshop.repo.CredentialRepository;
import sk.gw.jo2o.petshop.repo.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final CredentialRepository credentialRepository;

    public JwtData login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);




        return JwtData.builder()

                .build();
    }

    public void register(RegistrationData registrationData) {

    }

}
