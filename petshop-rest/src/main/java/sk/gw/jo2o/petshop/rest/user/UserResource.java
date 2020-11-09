package sk.gw.jo2o.petshop.rest.user;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.entity.User;
import sk.gw.jo2o.petshop.shopping.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
class UserResource {

    private final UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }

}
