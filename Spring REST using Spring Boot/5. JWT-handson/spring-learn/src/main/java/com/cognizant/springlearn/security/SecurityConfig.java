package com.cognizant.springlearn.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Spring Security 6 configuration (the exercise's WebSecurityConfigurerAdapter
 * was removed in Security 6).
 *  - In-memory users "admin" (ADMIN) and "user" (USER), password "pwd" (BCrypt).
 *  - HTTP Basic secures /authenticate.
 *  - JwtAuthorizationFilter validates Bearer tokens for all other requests.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public PasswordEncoder passwordEncoder() {
        LOGGER.info("Start");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("pwd")).roles("ADMIN").build();
        UserDetails user = User.withUsername("user")
                .password(encoder.encode("pwd")).roles("USER").build();
        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        // /authenticate is reachable by both roles via Basic auth
                        .requestMatchers("/authenticate").hasAnyRole("USER", "ADMIN")
                        // everything else requires a valid JWT (any authenticated user)
                        .anyRequest().authenticated())
                // validate the JWT before the basic-auth filter
                .addFilterBefore(new JwtAuthorizationFilter(), BasicAuthenticationFilter.class);
        return http.build();
    }
}
