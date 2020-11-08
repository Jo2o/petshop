package sk.gw.jo2o.petshop.auth.model;

import static lombok.AccessLevel.PRIVATE;

import java.util.Set;

import org.springframework.data.annotation.Immutable;

import lombok.*;

@Immutable
@Value
@Builder
@With
@AllArgsConstructor(access = PRIVATE)
public class RegistrationData {

    String username;

    String email;

    String password;

}
