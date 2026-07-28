package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** Resource-per-URL: all methods share "/countries" (naming guidelines). */
@RestController
@RequestMapping("/countries")
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    @GetMapping
    public List<Country> getAllCountries() {
        LOGGER.info("START");
        return countryService.getAllCountries();
    }

    @GetMapping("/{code}")
    public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
        LOGGER.info("START");
        return countryService.getCountry(code);
    }

    // POST: create -> payload validated via @Valid (global handler reports errors)
    @PostMapping
    public Country addCountry(@RequestBody @Valid Country country) {
        LOGGER.info("START");
        LOGGER.debug("Country: {}", country);
        LOGGER.info("END");
        return country;
    }

    // PUT: update -> payload in body
    @PutMapping
    public Country updateCountry(@RequestBody @Valid Country country) {
        LOGGER.info("START");
        LOGGER.debug("Country: {}", country);
        LOGGER.info("END");
        return country;
    }

    // DELETE: /countries/{code}
    @DeleteMapping("/{code}")
    public void deleteCountry(@PathVariable String code) {
        LOGGER.info("START");
        LOGGER.debug("Deleting country: {}", code);
        LOGGER.info("END");
    }
}
