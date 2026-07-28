package com.digitalnurture;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

/**
 * Exercise 3 — holds the JWT signing secret and derives a SecretKey from it.
 */
@Configuration
public class JwtConfig {

    @Value("${app.jwt.secret}")
    private String secret;

    public String getSecret() {
        return secret;
    }

    /** HS256 signing key derived from the configured secret. */
    public SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}
