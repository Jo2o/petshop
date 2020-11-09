package sk.gw.jo2o.petshop.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import sk.gw.jo2o.petshop.auth.service.*;

@EnableWebSecurity
@RequiredArgsConstructor
public class AuthConfig extends WebSecurityConfigurerAdapter {

    private static final String[] WHITELIST = {
            /* SWAGGER */
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/configuration/ui",
            "/configuration/security",
            "/webjars/**",

            /* PUBLIC API */
            "/v1/auth/**",
            "/v1/products/public-products/**"};

    private final AuthEntryPoint authEntryPoint;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(STATELESS).and()

                .authorizeRequests()
                .antMatchers().permitAll()

                .antMatchers(WHITELIST).permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }

    @Override
    @SneakyThrows
    public void configure(AuthenticationManagerBuilder auth) {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
