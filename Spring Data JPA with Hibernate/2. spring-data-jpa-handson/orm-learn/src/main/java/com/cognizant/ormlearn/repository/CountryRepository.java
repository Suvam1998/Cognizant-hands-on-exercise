package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    // Hands-on 1a: search by containing text.
    List<Country> findByNameContaining(String text);

    // Hands-on 1b: same search, sorted ascending by name.
    List<Country> findByNameContainingOrderByNameAsc(String text);

    // Hands-on 1c: countries starting with a given letter.
    List<Country> findByNameStartingWith(String prefix);
}
