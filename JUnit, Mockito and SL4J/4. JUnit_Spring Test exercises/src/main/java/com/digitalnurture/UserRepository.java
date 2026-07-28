package com.digitalnurture;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/** Exercise 2 & 7 (custom query method). */
public interface UserRepository extends JpaRepository<User, Long> {

    // Exercise 7: derived query method.
    List<User> findByName(String name);
}
