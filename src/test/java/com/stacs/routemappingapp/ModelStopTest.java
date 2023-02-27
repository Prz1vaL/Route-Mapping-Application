package com.stacs.routemappingapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.stacs.routemappingapp.model.stop.Stop;

@SpringBootTest
public class ModelStopTest {
	public void shouldCreateStop() {
		Stop stop = new Stop("N64", "NorthRoad", "ID", "Cardinal", "Dundee", "27/02/2023", "Monday", "13:00", "16:00");

        assertEquals(stop.getRouteNumber(), "N64");
        assertEquals(stop.getRouteName(), "NorthRoad");
        assertEquals(stop.getScheduleIdentifier(), "ID");
        assertEquals(stop.getStopName(), "Cardinal");
        assertEquals(stop.getStopLocation(), "Dundee");
        assertEquals(stop.getDate(), "27/02/2023");
        assertEquals(stop.getDay(), "Monday");
        assertEquals(stop.getDepartureTime(), "13:00");
        assertEquals(stop.getArrivalTime(), "16:00");
	}
}
