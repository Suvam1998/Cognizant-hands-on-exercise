package com.cognizant.springlearn;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringLearnApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    // No credentials / no token -> 401
    @Test
    void countriesWithoutAuthIsUnauthorized() throws Exception {
        mvc.perform(get("/countries"))
                .andExpect(status().isUnauthorized());
    }

    // Basic auth returns a JWT
    @Test
    void authenticateReturnsToken() throws Exception {
        mvc.perform(get("/authenticate").with(httpBasic("user", "pwd")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty());
    }

    // Wrong basic credentials -> 401
    @Test
    void authenticateWithWrongPasswordIsUnauthorized() throws Exception {
        mvc.perform(get("/authenticate").with(httpBasic("user", "wrong")))
                .andExpect(status().isUnauthorized());
    }

    // Full flow: get a token, then call /countries with the Bearer token
    @Test
    void countriesWithBearerTokenSucceeds() throws Exception {
        MvcResult result = mvc.perform(get("/authenticate").with(httpBasic("user", "pwd")))
                .andExpect(status().isOk())
                .andReturn();
        JsonNode json = objectMapper.readTree(result.getResponse().getContentAsString());
        String token = json.get("token").asText();

        mvc.perform(get("/countries").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].code").exists())
                .andExpect(jsonPath("$.length()").value(4));
    }

    // Tampered / invalid token -> 401
    @Test
    void countriesWithInvalidTokenIsUnauthorized() throws Exception {
        mvc.perform(get("/countries").header("Authorization", "Bearer invalid.token.value"))
                .andExpect(status().isUnauthorized());
    }
}
