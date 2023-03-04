package com.stacs.routemappingapp.model.route;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Route
 */
class RouteTest {
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Test for getUniqueRouteNumber
     */
    @Test
    void shouldGetUniqueRouteNumber() {
        Route route = restTemplate.getForObject("http://localhost:8080/routes/N64", Route.class);
        assertEquals(route.getUniqueRouteNumber(), "N64");
    }

    /**
     * Test for getRouteName
     */
    @Test
    void shouldGetRouteName() {
        Route route = restTemplate.getForObject("http://localhost:8080/routes/N64", Route.class);
        assertEquals(route.getRouteName(), "NorthRoad");
    }

    /**
     * Test for getDestination
     */
    @Test
    void shouldGetDestination() {
        Route route = restTemplate.getForObject("http://localhost:8080/routes/N64", Route.class);
        assertEquals(route.getDestination(), "StAndrews");
    }

    /**
     * Test for getStartingPoint
     */
    @Test
    void shouldGetStartingPoint() {
        Route route = restTemplate.getForObject("http://localhost:8080/routes/N64", Route.class);
        assertEquals(route.getStartingPoint(), "Dundee");
    }
}