package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    private static CountryService countryService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        LOGGER.info("Inside main");
        countryService = context.getBean(CountryService.class);

        // Hands-on 1: list all countries
        testGetAllCountries();
        // Hands-on 6: find by code
        testFindCountryByCode();
        // Hands-on 7: add
        testAddCountry();
        // Hands-on 8: update
        testUpdateCountry();
        // Hands-on 9: delete
        testDeleteCountry();
        // Hands-on 5: partial name search
        testFindByPartialName();
    }

    private static void testGetAllCountries() {
        LOGGER.info("Start testGetAllCountries");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("countries={}", countries);
        LOGGER.info("End testGetAllCountries");
    }

    private static void testFindCountryByCode() {
        LOGGER.info("Start testFindCountryByCode");
        try {
            Country country = countryService.findCountryByCode("IN");
            LOGGER.debug("Country:{}", country);
        } catch (CountryNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("End testFindCountryByCode");
    }

    private static void testAddCountry() {
        LOGGER.info("Start testAddCountry");
        try {
            Country country = new Country();
            country.setCode("XX");
            country.setName("New Country");
            countryService.addCountry(country);
            LOGGER.debug("Added: {}", countryService.findCountryByCode("XX"));
        } catch (CountryNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("End testAddCountry");
    }

    private static void testUpdateCountry() {
        LOGGER.info("Start testUpdateCountry");
        try {
            countryService.updateCountry("XX", "Updated Country");
            LOGGER.debug("Updated: {}", countryService.findCountryByCode("XX"));
        } catch (CountryNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("End testUpdateCountry");
    }

    private static void testDeleteCountry() {
        LOGGER.info("Start testDeleteCountry");
        countryService.deleteCountry("XX");
        LOGGER.debug("Deleted country XX");
        LOGGER.info("End testDeleteCountry");
    }

    private static void testFindByPartialName() {
        LOGGER.info("Start testFindByPartialName");
        List<Country> countries = countryService.findCountriesByPartialName("land");
        LOGGER.debug("countries matching 'land'={}", countries);
        LOGGER.info("End testFindByPartialName");
    }
}
