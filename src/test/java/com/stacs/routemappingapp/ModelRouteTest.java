package com.stacs.routemappingapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.stacs.routemappingapp.model.route.Route;

public class ModelRouteTest {
    @Test
	public void shouldCreateRoute() {
		Route route = new Route("N64", "NorthRoad", "StAndrews", "Dundee");

        assertEquals(route.getUniqueRouteNumber(), "N64");
        assertEquals(route.getRouteName(), "NorthRoad");
        assertEquals(route.getDestination(), "StAndrews");
        assertEquals(route.getStartingPoint(), "Dundee");
	}
}
