package com.cognizant.springlearn;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifies the Spring XML bean configuration for Hands-on 2, 4, 5, 6.
 * (Plain ClassPathXmlApplicationContext — no Spring Boot context needed.)
 */
class SpringLearnXmlTest {

    // Hands-on 2: SimpleDateFormat bean parses the date
    @Test
    void dateFormatBeanParsesDate() throws Exception {
        try (ClassPathXmlApplicationContext ctx =
                     new ClassPathXmlApplicationContext("date-format.xml")) {
            SimpleDateFormat format = ctx.getBean("dateFormat", SimpleDateFormat.class);
            Date date = format.parse("31/12/2018");
            assertThat(format.format(date)).isEqualTo("31/12/2018");
        }
    }

    // Hands-on 4: country bean has the configured properties
    @Test
    void countryBeanLoaded() {
        try (ClassPathXmlApplicationContext ctx =
                     new ClassPathXmlApplicationContext("country.xml")) {
            Country country = ctx.getBean("country", Country.class);
            assertThat(country.getCode()).isEqualTo("IN");
            assertThat(country.getName()).isEqualTo("India");
        }
    }

    // Hands-on 5: singleton -> same instance; prototype -> different instances
    @Test
    void singletonVsPrototypeScope() {
        try (ClassPathXmlApplicationContext ctx =
                     new ClassPathXmlApplicationContext("country.xml")) {
            Country a = ctx.getBean("country", Country.class);
            Country b = ctx.getBean("country", Country.class);
            assertThat(a).isSameAs(b);                 // singleton

            Country p1 = ctx.getBean("countryPrototype", Country.class);
            Country p2 = ctx.getBean("countryPrototype", Country.class);
            assertThat(p1).isNotSameAs(p2);            // prototype
        }
    }

    // Hands-on 6: list of four countries
    @Test
    @SuppressWarnings("unchecked")
    void countryListLoaded() {
        try (ClassPathXmlApplicationContext ctx =
                     new ClassPathXmlApplicationContext("country.xml")) {
            List<Country> countries = ctx.getBean("countryList", List.class);
            assertThat(countries).hasSize(4);
            assertThat(countries).extracting(Country::getCode)
                    .containsExactly("IN", "US", "DE", "JP");
        }
    }
}
