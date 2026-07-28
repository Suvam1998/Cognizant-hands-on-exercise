package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Country services. Every method is @Transactional so Spring manages the
 * Hibernate session and transaction boundaries.
 */
@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    /** Hands-on 1/5: list all countries. */
    @Transactional
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    /** Hands-on 6: find a country by its code, or fail. */
    @Transactional
    public Country findCountryByCode(String countryCode) throws CountryNotFoundException {
        Optional<Country> result = countryRepository.findById(countryCode);
        if (!result.isPresent()) {
            throw new CountryNotFoundException("Country not found for code: " + countryCode);
        }
        return result.get();
    }

    /** Hands-on 7: add a new country. */
    @Transactional
    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    /** Hands-on 8: update a country's name by code. */
    @Transactional
    public void updateCountry(String code, String name) throws CountryNotFoundException {
        Optional<Country> result = countryRepository.findById(code);
        if (!result.isPresent()) {
            throw new CountryNotFoundException("Country not found for code: " + code);
        }
        Country country = result.get();
        country.setName(name);
        countryRepository.save(country);
    }

    /** Hands-on 9: delete a country by code. */
    @Transactional
    public void deleteCountry(String code) {
        countryRepository.deleteById(code);
    }

    /** Hands-on 5: find countries by partial name. */
    @Transactional
    public List<Country> findCountriesByPartialName(String partialName) {
        return countryRepository.findByNameContaining(partialName);
    }
}
