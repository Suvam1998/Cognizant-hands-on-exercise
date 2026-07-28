package com.digitalnurture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * Exercise 2 — Resource Server (Spring Security 6 style).
 *
 * The endpoints are protected; access requires a valid Bearer JWT
 * (oauth2ResourceServer + jwt).
 *
 * In production you would point the resource server at your authorization
 * server with `spring.security.oauth2.resourceserver.jwt.issuer-uri` (or
 * jwk-set-uri) and let it fetch the signing keys. Here we provide a symmetric
 * (HS256) JwtDecoder bean so the sample validates tokens fully offline; the
 * auto-configured issuer-based decoder backs off because this bean exists.
 */
@Configuration
@EnableWebSecurity
public class ResourceServerConfig {

    @Value("${app.jwt.secret}")
    private String secret;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults()));
        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKeySpec key = new SecretKeySpec(
                secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(key).build();
    }
}
