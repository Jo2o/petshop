package sk.gw.jo2o.petshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] WHITELIST = {"/v2/api-docs", "/swagger*/**", "**"};

    private final UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers(WHITELIST);
    }

    @SneakyThrows
    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

//    @Bean
    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
