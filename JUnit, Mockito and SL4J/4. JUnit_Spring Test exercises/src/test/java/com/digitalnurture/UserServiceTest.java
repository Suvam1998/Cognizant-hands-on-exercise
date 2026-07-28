package com.digitalnurture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Exercise 2: Mocking a repository in a service test.
 * Exercise 6: Testing service exception handling for a missing user.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    // Exercise 2: repository returns a user.
    @Test
    void testGetUserById() {
        User user = new User(1L, "Alice");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertEquals("Alice", result.getName());
    }

    // Exercise 6a: getUserById returns null when the user is missing.
    @Test
    void testGetUserByIdReturnsNullWhenMissing() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        assertNull(userService.getUserById(99L));
    }

    // Exercise 6b: getUserByIdOrThrow throws for a missing user.
    @Test
    void testGetUserByIdOrThrowThrowsWhenMissing() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        NoSuchElementException ex = assertThrows(
                NoSuchElementException.class,
                () -> userService.getUserByIdOrThrow(99L));
        assertEquals("User not found with id: 99", ex.getMessage());
    }
}
