package sk.gw.jo2o.petshop.rest.auth;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.auth.model.RegistrationData;

@Service
@RequiredArgsConstructor
public class AuthMapper {

    public RegistrationData map(AuthRequest authRequest) {
        return RegistrationData.builder()
                .username(authRequest.getUsername())
                .password(authRequest.getPassword())
                .email(authRequest.getEmail())
                .roles(authRequest.getRoles())
                .build();
    }

}
