package com.example.apigateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApiGatewayApplicationTests {

    @Autowired
    private LogFilter logFilter;

    @Test
    void logFilterRegistered() {
        assertThat(logFilter).isNotNull();
    }
}
