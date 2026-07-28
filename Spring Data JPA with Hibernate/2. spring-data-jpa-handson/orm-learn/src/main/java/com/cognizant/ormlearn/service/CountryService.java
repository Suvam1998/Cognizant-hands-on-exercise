package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Transactional
    public List<Country> searchByName(String text) {
        return countryRepository.findByNameContaining(text);
    }

    @Transactional
    public List<Country> searchByNameSorted(String text) {
        return countryRepository.findByNameContainingOrderByNameAsc(text);
    }

    @Transactional
    public List<Country> searchByStartingLetter(String prefix) {
        return countryRepository.findByNameStartingWith(prefix);
    }
}
