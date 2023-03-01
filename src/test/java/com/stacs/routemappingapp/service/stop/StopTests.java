package com.stacs.routemappingapp.service.stop;

import org.junit.jupiter.api.Test;

import com.stacs.routemappingapp.model.stop.Stop;
import com.stacs.routemappingapp.service.StopService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


import java.util.HashMap;
import java.util.Map;

public class StopTests {

    private static StopService stopService;

    @BeforeAll
    public static void setup() {
        stopService = new StopService();
    }

    /*
     * Removes all stops from the StopService
     */
    @AfterEach
    public void wipeStops() {
        stopService.wipeStops();
    }

    /*
     * Test checkIfStopIDValid with a valid ID.
     */
    @Test
    public void shouldCheckIfStopIDValidWithValidID() {
        assertDoesNotThrow(() -> {
            String stopNumber = "123";
            stopService.checkIfStopIDValid(stopNumber);
        });
    }

    /*
     * Test checkAlphaNumeric with invalid name.
     */
    @Test
    public void shouldCheckIfStopIDValidWithNoID() {
        assertThrows(IllegalArgumentException.class, () -> {
            String stopNumber = "123";
            stopService.checkIfStopIDValid(stopNumber);
        });
    }

    /*
     * Test getDayOfWeek with correct day.
     */
    @Test
    public void shouldGetDayOfWeekSuccessful() {
        String date = "07/03/2023";
        String expectedDayOfWeek = "Tuesday";
  
        assertEquals(expectedDayOfWeek, stopService.getDayOfWeek(date));
    }

    /*
     * Test getDayOfWeek with incorrect day.
     */
    @Test
    public void shouldGetDayOfWeekUnsuccessful() {
        String date = "07/03/2023";
        String expectedDayOfWeek = "Friday";
  
        assertNotEquals(expectedDayOfWeek, stopService.getDayOfWeek(date));
    }

    /*
     * Test addStop with valid parameters.
     */
    @Test
    public void shouldAddStopWithValidParameters() {
        assertDoesNotThrow(() -> {
            stopService.addStop("R1", "Route1", "65ID", "Cardinal", "Dundee", "07/03/2023", "Tuesday", "13:00", "16:00");
        });
    }

    /*
     * Test addStop with existing scheduleIdentifier
     */
    @Test
    public void shouldAddStopWithExistingID() {
        assertThrows(IllegalArgumentException.class, () -> {
            stopService.addStop("R1", "Route1", "65ID", "Cardinal", "Dundee", "07/03/2023", "Tuesday", "13:00", "16:00");
            stopService.addStop("R1", "Route1", "65ID", "Cardinal", "Dundee", "07/03/2023", "Tuesday", "13:00", "16:00");
        });
    }

    /*
     * Test viewRoutesByStop with valid parameters.
     */
    @Test
    public void shouldViewRoutesByStop() {
        stopService.addStop("R1", "Route1", "65ID", "Cardinal", "Dundee", "07/03/2023", "Tuesday", "13:00", "16:00");

        assertDoesNotThrow(() -> {
            stopService.viewRoutesByStop("Cardinal");
        });
    }

    /*
     * Test viewRoutesByStop with invalid parameters.
     */
    @Test
    public void shouldViewRoutesByStopInvalid() {
        stopService.addStop("R1", "Route1", "65ID", "Cardinal", "Dundee", "07/03/2023", "Tuesday", "13:00", "16:00");

        assertThrows(IllegalArgumentException.class, () -> {
            stopService.viewRoutesByStop("");
        });
    }
}
