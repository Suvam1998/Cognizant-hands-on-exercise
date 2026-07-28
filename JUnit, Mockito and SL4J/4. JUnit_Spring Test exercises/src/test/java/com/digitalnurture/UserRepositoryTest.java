package com.digitalnurture;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Exercise 7: Test a custom repository query (findByName).
 * @DataJpaTest spins up an in-memory JPA slice with a real (H2) database.
 */
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByName() {
        userRepository.save(new User("Alice"));
        userRepository.save(new User("Alice"));
        userRepository.save(new User("Bob"));

        List<User> alices = userRepository.findByName("Alice");
        List<User> bobs = userRepository.findByName("Bob");
        List<User> none = userRepository.findByName("Charlie");

        assertThat(alices).hasSize(2);
        assertThat(alices).allMatch(u -> u.getName().equals("Alice"));
        assertThat(bobs).hasSize(1);
        assertThat(none).isEmpty();
    }
}
