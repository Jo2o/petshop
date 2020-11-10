package sk.gw.jo2o.petshop.shopping.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.entity.User;
import sk.gw.jo2o.petshop.exception.PetShopNotFoundException;
import sk.gw.jo2o.petshop.repo.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User find(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new PetShopNotFoundException("Cannot find user with userId: " + userId));
    }

    public List<User> save(List<User> users) {
        return userRepository.saveAll(users);
    }

}
