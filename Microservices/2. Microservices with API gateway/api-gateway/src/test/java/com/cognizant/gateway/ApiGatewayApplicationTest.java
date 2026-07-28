package com.cognizant.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifies the gateway context loads and the global LogFilter is registered.
 */
@SpringBootTest
class ApiGatewayApplicationTest {

    @Autowired
    private LogFilter logFilter;

    @Test
    void logFilterBeanExists() {
        assertThat(logFilter).isNotNull();
    }
}
