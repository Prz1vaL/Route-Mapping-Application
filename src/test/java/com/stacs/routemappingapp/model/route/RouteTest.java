package com.stacs.routemappingapp.model.route;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RouteTest {
    Route route = new Route("N64", "NorthRoad", "StAndrews", "Dundee");

    @Test
    void getUniqueRouteNumber() {
        assertEquals(route.getUniqueRouteNumber(), "N64");
    }

    @Test
    void getRouteName() {
        assertEquals(route.getRouteName(), "NorthRoad");
    }

    @Test
    void getDestination() {
        assertEquals(route.getDestination(), "StAndrews");
    }

    @Test
    void getStartingPoint() {
        assertEquals(route.getStartingPoint(), "Dundee");
    }
}