package com.digitalnurture;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Verifies:
 *  - context loads,
 *  - the custom RandomLoadBalancer bean is present,
 *  - the "load_balanced_route" (lb://example-service) is registered.
 */
@SpringBootTest
class LoadBalancingGatewayTest {

    @Autowired
    private RouteLocator routeLocator;

    @Autowired
    private ReactorLoadBalancer<ServiceInstance> randomLoadBalancer;

    @Test
    void customLoadBalancerBeanExists() {
        assertThat(randomLoadBalancer).isNotNull();
    }

    @Test
    void loadBalancedRouteIsRegistered() {
        List<Route> routes = Flux.from(routeLocator.getRoutes()).collectList().block();
        assertThat(routes).isNotNull();
        Route lb = routes.stream()
                .filter(r -> r.getId().equals("load_balanced_route"))
                .findFirst()
                .orElseThrow();
        assertThat(lb.getUri().toString()).isEqualTo("lb://example-service");
    }
}
