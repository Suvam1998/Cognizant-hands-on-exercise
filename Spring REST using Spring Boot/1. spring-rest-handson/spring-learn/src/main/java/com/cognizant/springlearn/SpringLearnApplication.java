package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringLearnApplication.class, args);
        LOGGER.info("Inside main");
        displayDate();       // Hands-on 2
        displayCountry();    // Hands-on 4 & 5
        displayCountries();  // Hands-on 6
    }

    // ---- Hands-on 2 & 3: load SimpleDateFormat from XML, log the parsed date ----
    public static void displayDate() {
        LOGGER.info("START");
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("date-format.xml")) {
            SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
            Date date = format.parse("31/12/2018");
            LOGGER.debug("Date: {}", date);
        } catch (Exception e) {
            LOGGER.error("Error parsing date", e);
        }
        LOGGER.info("END");
    }

    // ---- Hands-on 4 & 5: load a single Country bean (singleton scope) ----
    public static void displayCountry() {
        LOGGER.info("START");
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("country.xml")) {
            Country country = context.getBean("country", Country.class);
            // Hands-on 5: second reference -> same instance for singleton scope
            Country anotherCountry = context.getBean("country", Country.class);
            LOGGER.debug("Country : {}", country.toString());
            LOGGER.debug("Same instance (singleton)? {}", country == anotherCountry);
        }
        LOGGER.info("END");
    }

    // ---- Hands-on 6: load the list of countries ----
    @SuppressWarnings("unchecked")
    public static void displayCountries() {
        LOGGER.info("START");
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("country.xml")) {
            List<Country> countryList = context.getBean("countryList", List.class);
            LOGGER.debug("Country List: {}", countryList);
        }
        LOGGER.info("END");
    }
}
