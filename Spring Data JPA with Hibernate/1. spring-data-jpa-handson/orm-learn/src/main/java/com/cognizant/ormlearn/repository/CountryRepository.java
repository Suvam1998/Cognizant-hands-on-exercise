package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    /**
     * Hands-on 5: find countries whose name contains the given text
     * (a derived query method — Spring Data generates the query).
     */
    List<Country> findByNameContaining(String partialName);
}
