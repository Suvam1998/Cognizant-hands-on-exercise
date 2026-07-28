package com.digitalnurture;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifies the gateway wiring:
 *  - the context loads,
 *  - the LoggingFilter GlobalFilter is registered,
 *  - the "example_route" defined in application.properties is present.
 */
@SpringBootTest
class GatewayRoutesTest {

    @Autowired
    private RouteLocator routeLocator;

    @Autowired
    private LoggingFilter loggingFilter;

    @Test
    void loggingFilterBeanExists() {
        assertThat(loggingFilter).isNotNull();
    }

    @Test
    void exampleRouteIsRegistered() {
        List<Route> routes = Flux.from(routeLocator.getRoutes()).collectList().block();
        assertThat(routes).isNotNull();
        assertThat(routes)
                .anyMatch(r -> r.getId().equals("example_route"));
    }
}
