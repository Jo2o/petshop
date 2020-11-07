package sk.gw.jo2o.petshop.auth.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public String signIn(String username, String password) {
        return "signin";
    }

//    public String signUp(AuthRequest authRequest) {
//        return "signup";
//    }

}
