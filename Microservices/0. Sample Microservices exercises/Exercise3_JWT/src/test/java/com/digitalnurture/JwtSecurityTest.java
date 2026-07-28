package com.digitalnurture;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * End-to-end flow: obtain a token from /auth/login, then use it to reach the
 * protected /secure endpoint. Also checks that requests without / with an
 * invalid token are rejected.
 */
@SpringBootTest
@AutoConfigureMockMvc
class JwtSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void loginThenAccessSecureEndpoint() throws Exception {
        // 1. Obtain a token (public endpoint).
        MvcResult loginResult = mockMvc.perform(post("/auth/login").param("username", "alice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andReturn();

        JsonNode json = objectMapper.readTree(loginResult.getResponse().getContentAsString());
        String token = json.get("token").asText();

        // 2. Use the token to reach the secured endpoint.
        mockMvc.perform(get("/secure").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello alice")));
    }

    @Test
    void secureEndpointRejectsMissingToken() throws Exception {
        mockMvc.perform(get("/secure"))
                .andExpect(status().isForbidden());
    }

    @Test
    void secureEndpointRejectsInvalidToken() throws Exception {
        mockMvc.perform(get("/secure").header("Authorization", "Bearer not-a-real-token"))
                .andExpect(status().isForbidden());
    }
}
