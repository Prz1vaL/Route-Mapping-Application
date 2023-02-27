package com.stacs.routemappingapp.model.stop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StopTest {
    Stop stop = new Stop("N64", "NorthRoad", "ID", "Cardinal", "Dundee", "27/02/2023", "Monday", "13:00", "16:00");











    @Test
    void getScheduleIdentifier() {
        assertEquals(stop.getScheduleIdentifier(), "ID");
    }

    @Test
    void getStopName() {
        assertEquals(stop.getStopName(), "Cardinal");
    }

    @Test
    void getRouteNumber() {
        assertEquals(stop.getRouteNumber(), "N64");
    }

    @Test
    void getRouteName() {
        assertEquals(stop.getRouteName(), "NorthRoad");
    }

    @Test
    void getStopLocation() {
        assertEquals(stop.getStopLocation(), "Dundee");
    }

    @Test
    void getDate() {
        assertEquals(stop.getDate(), "27/02/2023");
    }

    @Test
    void getDay() {
        assertEquals(stop.getDay(), "Monday");
    }

    @Test
    void getDepartureTime() {
        assertEquals(stop.getDepartureTime(), "13:00");
    }

    @Test
    void getArrivalTime() {
        assertEquals(stop.getArrivalTime(), "16:00");
    }
}