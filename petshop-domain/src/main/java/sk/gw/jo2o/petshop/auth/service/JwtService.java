package sk.gw.jo2o.petshop.auth.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration.ms}")
    private int expiration;

    public String generate(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validate(String authToken) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        } catch (RuntimeException e) {
            log.error("JWT auth exception caught: " + e.toString());
        }
        return false;
    }

}
