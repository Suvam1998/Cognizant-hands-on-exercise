package com.digitalnurture;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Exercise 3 — creates, validates, and reads authentication from JWTs.
 * (Completed and modernized to the jjwt 0.12.x API.)
 */
@Component
public class JwtTokenProvider {

    private static final long VALIDITY_MS = 3_600_000; // 1 hour

    @Autowired
    private JwtConfig jwtConfig;

    /** Build a signed JWT for the given username. */
    public String createToken(String username) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + VALIDITY_MS);
        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(validity)
                .signWith(jwtConfig.getSigningKey())
                .compact();
    }

    /** True if the token's signature and expiry are valid. */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(jwtConfig.getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    /** Build a Spring Security Authentication from a valid token. */
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(jwtConfig.getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        String username = claims.getSubject();
        return new UsernamePasswordAuthenticationToken(
                username, null, List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
