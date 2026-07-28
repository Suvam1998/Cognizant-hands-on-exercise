package com.digitalnurture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/** Exercises 2, 5, 6, 7. */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /** Exercise 2 & 3: returns the user, or null if not found. */
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /** Exercise 6: throws NoSuchElementException when the user is missing. */
    public User getUserByIdOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    /** Exercise 5: persist a user. */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /** Exercise 7: custom query passthrough. */
    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }
}
