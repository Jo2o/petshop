package sk.gw.jo2o.petshop.auth.service;

import static sk.gw.jo2o.petshop.auth.model.enums.Role.USER;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.exception.PetShopNotFoundException;
import sk.gw.jo2o.petshop.repo.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        sk.gw.jo2o.petshop.entity.User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new PetShopNotFoundException("Cannot find user with username: " + username);
        }

        return User.withUsername(username)
                .password(user.getCredential().getPassword())
                .roles(USER.name())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}
