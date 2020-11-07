package sk.gw.jo2o.petshop.rest.auth;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.auth.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
class AuthResource {

    private final AuthService authService;

    @PostMapping("/signup")
    public String signUp(AuthRequest authRequest) {
//        return authService.signUp(authRequest);
        return "signup";
    }

    @PostMapping("/signin")
    public String signIn(@RequestParam String username, @RequestParam String password) {
        return authService.signIn(username, password);
    }
}
