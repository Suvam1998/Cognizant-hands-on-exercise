package com.digitalnurture;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Exercise 3 — public endpoint to obtain a JWT.
 * In a real system this would authenticate the credentials first; here it just
 * issues a token for the supplied username to demonstrate the flow.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String username) {
        return Map.of("token", jwtTokenProvider.createToken(username));
    }
}
