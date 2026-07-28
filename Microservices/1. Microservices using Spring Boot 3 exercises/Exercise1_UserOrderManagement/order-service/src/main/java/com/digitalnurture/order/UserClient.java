package com.digitalnurture.order;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Calls the User Service over REST using WebClient to verify a user exists
 * before an order is created (inter-service communication).
 */
@Component
public class UserClient {

    private final WebClient userWebClient;

    public UserClient(WebClient userWebClient) {
        this.userWebClient = userWebClient;
    }

    /** Returns the user, or null if the user does not exist (404). */
    public UserDto getUser(Long userId) {
        try {
            return userWebClient.get()
                    .uri("/users/{id}", userId)
                    .retrieve()
                    .bodyToMono(UserDto.class)
                    .block();
        } catch (WebClientResponseException.NotFound ex) {
            return null;
        }
    }
}
