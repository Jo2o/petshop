package sk.gw.jo2o.petshop.auth.service;

import static org.springframework.util.StringUtils.hasText;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import sk.gw.jo2o.petshop.exception.PetShopAuthException;

@Slf4j
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) {
        String jwt = parseJwt(httpServletRequest);
        if (jwt != null && jwtService.validate(jwt)) {
            String username = jwtService.getUserNameFromToken(jwt);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        if (jwt != null && !jwtService.validate(jwt)) {
            throw new PetShopAuthException("Invalid token!");
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (hasText(headerAuth)) {
            return headerAuth.replace("Bearer ", "");
        }
        return null;
    }

}
