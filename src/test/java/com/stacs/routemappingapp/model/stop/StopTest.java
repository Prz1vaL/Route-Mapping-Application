package com.stacs.routemappingapp.model.stop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Stop
 */
class StopTest {
    Stop stop = new Stop("N64", "NorthRoad", "ID", "Cardinal", "Dundee", "27/02/2023", "Monday", "13:00", "16:00");


    /**
     * Test for getScheduleIdentifier
     */
    @Test
    void getScheduleIdentifier() {
        assertEquals(stop.getScheduleIdentifier(), "ID");
    }

    /**
     * Test for getStopName
     */
    @Test
    void getStopName() {
        assertEquals(stop.getStopName(), "Cardinal");
    }

    /**
     * Test for getRouteNumber
     */
    @Test
    void getRouteNumber() {
        //assertEquals(stop.getRouteNumber(), "N64");
    }

    /**
     * Test for getRouteName
     */
    @Test
    void getRouteName() {
        assertEquals(stop.getRouteName(), "NorthRoad");
    }

    /**
     * Test for getStopLocation
     */
    @Test
    void getStopLocation() {
        assertEquals(stop.getStopLocation(), "Dundee");
    }

    /**
     * Test for getDate
     */
    @Test
    void getDate() {
        assertEquals(stop.getDate(), "27/02/2023");
    }

    /**
     * Test for getDay
     */
    @Test
    void getDay() {
        assertEquals(stop.getDay(), "Monday");
    }

    /**
     * Test for getDepartureTime
     */
    @Test
    void getDepartureTime() {
        assertEquals(stop.getDepartureTime(), "13:00");
    }

    /**
     * Test for getArrivalTime
     */
    @Test
    void getArrivalTime() {
        assertEquals(stop.getArrivalTime(), "16:00");
    }
}