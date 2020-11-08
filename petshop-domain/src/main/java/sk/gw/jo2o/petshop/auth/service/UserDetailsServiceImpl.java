package sk.gw.jo2o.petshop.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
        sk.gw.jo2o.petshop.entity.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new PetShopNotFoundException("Cannot find user with username: " + username));
        return UserDetailsImpl.build(user);
    }

}
