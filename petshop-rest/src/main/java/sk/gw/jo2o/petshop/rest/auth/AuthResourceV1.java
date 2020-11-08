package sk.gw.jo2o.petshop.rest.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import sk.gw.jo2o.petshop.auth.model.JwtData;
import sk.gw.jo2o.petshop.auth.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
class AuthResourceV1 {

    private final AuthMapper authMapper;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest authRequest) {
        authService.register(authMapper.map(authRequest));
        return ResponseEntity.ok("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtData> login(@RequestParam String username, @RequestParam String password) {
        return ResponseEntity.ok(authService.login(username, password));
    }
}
