package com.stacs.routemappingapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.stacs.routemappingapp.model.route.Route;

public class ModelRouteTest {
    @Test
	public void shouldAddRoute() {
		Route route = new Route("N64", "NorthRoad", "StAndrews", "Dundee");

        assertTrue(route.getUniqueRouteNumber(), "N64");
        assertTrue(route.getRouteName(), "NorthRoad");
        assertTrue(route.getDestination(), "StAndrews");
        assertTrue(route.getStartingPoint(), "Dundee");
	}
}
