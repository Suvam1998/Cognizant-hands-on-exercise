package com.cognizant.springlearn.security;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

/**
 * Shared JWT signing key + expiry. The secret is >= 256 bits, as required by
 * jjwt 0.12.x for HS256 (the exercise's short "secretkey" only worked with the
 * old 0.9.0 library).
 */
public final class JwtConstants {

    public static final String SECRET = "secretkeysecretkeysecretkeysecret"; // 33 chars
    public static final SecretKey KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    public static final long EXPIRY_MS = 1200000L; // 20 minutes

    private JwtConstants() {
    }
}
