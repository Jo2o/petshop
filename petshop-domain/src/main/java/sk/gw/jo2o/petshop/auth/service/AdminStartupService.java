package sk.gw.jo2o.petshop.auth.service;

import static java.lang.Boolean.TRUE;
import static sk.gw.jo2o.petshop.auth.model.Role.ADMIN;
import static sk.gw.jo2o.petshop.auth.model.Role.USER;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.entity.Credential;
import sk.gw.jo2o.petshop.entity.User;
import sk.gw.jo2o.petshop.repo.CredentialRepository;
import sk.gw.jo2o.petshop.repo.UserRepository;

@Service
@RequiredArgsConstructor
public class AdminStartupService {

    private static final String ADMIN_USERNAME = "admin";

    private final CredentialRepository credentialRepository;
    private final UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void setupAdminUser() {
        if (TRUE.equals(userRepository.existsByUsername(ADMIN_USERNAME))) {
            return;
        }

        Credential credential = credentialRepository.save(Credential.builder()
                .password("$2a$10$8qBXpkzi40GPwAasQhNAq.xTpby675wWT4uwbxJnFen2yNKEoZ7kG")
                .roles(ADMIN.name() + ',' + USER.name())
                .build());

        userRepository.save(User.builder()
                .username(ADMIN_USERNAME)
                .email("N/A")
                .credential(credential)
                .build());

    }

}
