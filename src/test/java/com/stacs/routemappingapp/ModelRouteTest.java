package com.stacs.routemappingapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.stacs.routemappingapp.model.route.Route;

public class ModelRouteTest {
    @Test
	public void shouldAddRoute() {
		Route route = new Route("N64", "NorthRoad", "StAndrews", "Dundee");

        assertEqual(route.getUniqueRouteNumber(), "N64");
        assertEqual(route.getRouteName(), "NorthRoad");
        assertEqual(route.getDestination(), "StAndrews");
        assertEqual(route.getStartingPoint(), "Dundee");
	}
}
