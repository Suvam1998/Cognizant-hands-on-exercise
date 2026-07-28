package com.digitalnurture;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Verifies the OAuth2 login security config:
 *  - context loads with the configured client registration,
 *  - an unauthenticated request is redirected into the OAuth2 login flow,
 *  - an (simulated) OIDC-authenticated request reaches the controller.
 */
@SpringBootTest
@AutoConfigureMockMvc
class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        // If the application context fails to start this test fails.
    }

    @Test
    void unauthenticatedRequestIsRedirectedToLogin() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/oauth2/authorization/my-client"));
    }

    @Test
    void authenticatedRequestReachesController() throws Exception {
        mockMvc.perform(get("/user")
                        .with(oidcLogin().idToken(t -> t.subject("alice"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("alice"));
    }
}
