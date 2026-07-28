package com.digitalnurture;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * Exercise 1 controller.
 *
 * /user returns the authenticated principal's name (the raw Principal is not
 * returned as JSON to avoid serializing the whole OAuth2 token graph).
 * /userinfo returns the OIDC claims of the logged-in user.
 */
@RestController
public class UserController {

    @GetMapping("/user")
    public Map<String, Object> user(Principal principal) {
        return Map.of("name", principal.getName());
    }

    @GetMapping("/userinfo")
    public Map<String, Object> userInfo(@AuthenticationPrincipal OidcUser oidcUser) {
        return Map.of(
                "sub", oidcUser.getSubject(),
                "email", String.valueOf(oidcUser.getEmail()),
                "claims", oidcUser.getClaims());
    }
}
