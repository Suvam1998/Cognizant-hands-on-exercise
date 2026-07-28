package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.security.JwtConstants;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT step 1: authenticate with Basic credentials and return a signed token.
 */
@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @GetMapping("/authenticate")
    public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
        LOGGER.info("Start");
        LOGGER.debug("Authorization header: {}", authHeader);

        String user = getUser(authHeader);
        String token = generateJwt(user);

        Map<String, String> map = new HashMap<>();
        map.put("token", token);

        LOGGER.info("End");
        return map;
    }

    /** Decode the Base64 "Basic ..." header and extract the username. */
    private String getUser(String authHeader) {
        LOGGER.debug("Start getUser");
        String encodedCredentials = authHeader.replace("Basic ", "");
        byte[] decoded = Base64.getDecoder().decode(encodedCredentials);
        String credentials = new String(decoded, StandardCharsets.UTF_8);   // user:password
        String user = credentials.substring(0, credentials.indexOf(":"));
        LOGGER.debug("User: {}", user);
        return user;
    }

    /** Build a signed JWT for the user with a 20-minute expiry. */
    private String generateJwt(String user) {
        LOGGER.debug("Start generateJwt for user {}", user);
        Date now = new Date();
        JwtBuilder builder = Jwts.builder()
                .subject(user)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + JwtConstants.EXPIRY_MS))
                .signWith(JwtConstants.KEY);
        return builder.compact();
    }
}
