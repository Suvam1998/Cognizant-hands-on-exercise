package com.digitalnurture.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifies the gateway context loads and both routes (with path rewriting)
 * are registered.
 */
@SpringBootTest
class GatewayRoutesTest {

    @Autowired
    private RouteLocator routeLocator;

    @Test
    void customerAndBillingRoutesRegistered() {
        List<Route> routes = Flux.from(routeLocator.getRoutes()).collectList().block();
        assertThat(routes).isNotNull();
        assertThat(routes).anyMatch(r -> r.getId().equals("customer_route"));
        assertThat(routes).anyMatch(r -> r.getId().equals("billing_route"));
    }
}
