package com.stacs.routemappingapp.model.route;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Route
 */
class RouteTest {
    Route route = new Route("N64", "NorthRoad", "StAndrews", "Dundee");

    /**
     * Test for getUniqueRouteNumber
     */
    @Test
    void getUniqueRouteNumber() {
        assertEquals(route.getUniqueRouteNumber(), "N64");
    }

    /**
     * Test for getRouteName
     */
    @Test
    void getRouteName() {
        assertEquals(route.getRouteName(), "NorthRoad");
    }

    /**
     * Test for getDestination
     */
    @Test
    void getDestination() {
        assertEquals(route.getDestination(), "StAndrews");
    }

    /**
     * Test for getStartingPoint
     */
    @Test
    void getStartingPoint() {
        assertEquals(route.getStartingPoint(), "Dundee");
    }
}