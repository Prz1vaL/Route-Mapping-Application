package com.stacs.routemappingapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.stacs.routemappingapp.model.stop.Stop;

@SpringBootTest
public class ModelStopTest {
	public void shouldCreateStop() {
		Stop stop = new Stop("N64", "NorthRoad", "ID", "Cardinal", "Dundee", "27/02/2023", "Monday", "13:00", "16:00");

        assertEqual(stop.getRouteNumber(), "N64");
        assertEqual(stop.getRouteName(), "NorthRoad");
        assertEqual(stop.getScheduleIdentifier(), "ID");
        assertEqual(stop.getStopName(), "Cardinal");
        assertEqual(stop.getStopLocation(), "Dundee");
        assertEqual(stop.getDate(), "27/02/2023");
        assertEqual(stop.getDay(), "Monday");
        assertEqual(stop.getDepartureTime(), "13:00");
        assertEqual(stop.getArrivalTime(), "16:00");
	}
}
