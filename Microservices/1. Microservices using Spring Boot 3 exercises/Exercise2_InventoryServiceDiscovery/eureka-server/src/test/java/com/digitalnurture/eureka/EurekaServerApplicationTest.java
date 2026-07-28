package com.digitalnurture.eureka;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Verifies the Eureka registry application context starts. Uses a random port
 * so the test does not need port 8761 free.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EurekaServerApplicationTest {

    @Test
    void contextLoads() {
    }
}
