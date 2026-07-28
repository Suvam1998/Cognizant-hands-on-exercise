package com.digitalnurture;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SecureController {

    /** Requires a valid JWT (populated into the SecurityContext by the filter). */
    @GetMapping("/secure")
    public String secure(Principal principal) {
        return "Hello " + principal.getName() + ", this is a secure endpoint";
    }
}
