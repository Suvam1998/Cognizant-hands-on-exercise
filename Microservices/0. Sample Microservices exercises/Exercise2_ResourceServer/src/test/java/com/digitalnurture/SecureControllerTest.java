package com.digitalnurture;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Verifies the resource server:
 *  - no token  -> 401 Unauthorized,
 *  - valid HS256 JWT -> 200 and the secured body.
 *
 * The token is signed with the same shared secret the JwtDecoder uses, so this
 * exercises the real decode/validate path.
 */
@SpringBootTest
@AutoConfigureMockMvc
class SecureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${app.jwt.secret}")
    private String secret;

    private String validToken() throws Exception {
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject("alice")
                .issueTime(Date.from(Instant.now()))
                .expirationTime(Date.from(Instant.now().plusSeconds(3600)))
                .build();
        SignedJWT jwt = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claims);
        jwt.sign(new MACSigner(secret.getBytes(StandardCharsets.UTF_8)));
        return jwt.serialize();
    }

    @Test
    void secureEndpointRejectsRequestWithoutToken() throws Exception {
        mockMvc.perform(get("/secure"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void secureEndpointAllowsValidToken() throws Exception {
        mockMvc.perform(get("/secure")
                        .header("Authorization", "Bearer " + validToken()))
                .andExpect(status().isOk())
                .andExpect(content().string("This is a secure endpoint"));
    }
}
