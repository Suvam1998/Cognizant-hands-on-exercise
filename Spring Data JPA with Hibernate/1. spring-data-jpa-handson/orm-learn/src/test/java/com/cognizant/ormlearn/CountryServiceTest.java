package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Verifies the Country services (Hands-on 5–9) against an in-memory H2 database
 * populated from src/test/resources/data.sql.
 */
@SpringBootTest
class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Test
    void getAllCountries() {
        List<Country> all = countryService.getAllCountries();
        assertThat(all).hasSizeGreaterThan(200);
    }

    @Test
    void findCountryByCode() throws CountryNotFoundException {
        Country india = countryService.findCountryByCode("IN");
        assertThat(india.getName()).isEqualTo("India");
    }

    @Test
    void findCountryByCode_notFound() {
        assertThrows(CountryNotFoundException.class,
                () -> countryService.findCountryByCode("ZZ"));
    }

    @Test
    void addFindUpdateDeleteCountry() throws CountryNotFoundException {
        // Hands-on 7: add
        Country c = new Country();
        c.setCode("QZ");
        c.setName("Testland");
        countryService.addCountry(c);
        assertThat(countryService.findCountryByCode("QZ").getName()).isEqualTo("Testland");

        // Hands-on 8: update
        countryService.updateCountry("QZ", "Updatedland");
        assertThat(countryService.findCountryByCode("QZ").getName()).isEqualTo("Updatedland");

        // Hands-on 9: delete
        countryService.deleteCountry("QZ");
        assertThrows(CountryNotFoundException.class,
                () -> countryService.findCountryByCode("QZ"));
    }

    @Test
    void findByPartialName() {
        List<Country> matches = countryService.findCountriesByPartialName("land");
        assertThat(matches).extracting(Country::getName)
                .contains("Finland", "Iceland", "Ireland", "Poland");
    }
}
