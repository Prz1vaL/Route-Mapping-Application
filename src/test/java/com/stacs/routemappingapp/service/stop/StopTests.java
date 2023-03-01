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
     * Test checkIfDateValid with valid date.
     */
    @Test
    public void shouldCheckIfDateValid(){
        assertDoesNotThrow(() -> {
            stopService.checkIfDateValid("07/03/2023");
        });
    }

    /*
     * Test checkIfDateValid with invalid date.
     */
    @Test
    public void shouldCheckIfDateValidWithInvalidDate(){
        assertThrows(IllegalArgumentException.class, () -> {
            stopService.checkIfDateValid("test/03/2023");
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

    /*
     * Test checkIfTimeValid with valid time.
     */
    @Test
    public void shouldCheckIfTimeValid() {
        assertDoesNotThrow(() -> {
            stopService.checkIfTimeValid("16:00");
        });
    }

    /*
     * Test checkIfTimeValid with invalid time.
     */
    @Test
    public void shouldCheckIfTimeValidWithInvalidTime(){
        assertThrows(IllegalArgumentException.class, () -> {
            stopService.checkIfTimeValid("34:30");
        });
    }

    /*
     * Test checkIfTimeValid with no time.
     */
    @Test
    public void shouldCheckIfTimeValidWithNoTime(){
        assertThrows(IllegalArgumentException.class, () -> {
            stopService.checkIfTimeValid("");
        });
    }

    /*
     * Test checkStopNamingConvention with valid time.
     */
    @Test
    public void shouldCheckStopNamingConvention() {
        assertDoesNotThrow(() -> {
            stopService.checkStopNamingConvention("Valid10");
        });
    }

    /*
     * Test checkStopNamingConvention with invalid name.
     */
    @Test
    public void shouldCheckStopNamingConventionWithInvalidName(){
        assertThrows(IllegalArgumentException.class, () -> {
            stopService.checkStopNamingConvention("V_a-l.i!d10");
        });
    }

    /*
     * Test checkStopNamingConvention with no name.
     */
    @Test
    public void shouldCheckStopNamingConventionWithNoName(){
        assertThrows(IllegalArgumentException.class, () -> {
            stopService.checkStopNamingConvention("");
        });
    }

    /*
     * Test checkIfScheduleIdentifierValid with valid scheduleIdentifier.
     */
    @Test
    public void shouldCheckIfScheduleIdentifierValid() {
        assertDoesNotThrow(() -> {
            stopService.checkIfScheduleIdentifierValid("R1");
        });
    }

    /*
     * Test checkIfScheduleIdentifierValid with no scheduleIdentifier.
     */
    @Test
    public void shouldCheckIfScheduleIdentifierValidWithNoSI(){
        assertThrows(IllegalArgumentException.class, () -> {
            stopService.checkIfScheduleIdentifierValid("");
        });
    }

    /*
     * Test isBeforeTime with valid times.
     */
    @Test
    public void shouldIsBeforeTime() {
        assertDoesNotThrow(() -> {
            stopService.isBeforeTime("16:00", "13:00");
        });
    }

    /*
     * Test isBeforeTime with depature after arrival time.
     */
    @Test
    public void shouldIsBeforeTimeWithWithInvalidTimes(){
        assertThrows(IllegalArgumentException.class, () -> {
            stopService.isBeforeTime("13:00", "16:00");
        });
    }

    /*
     * Test checkIfStopNameValid with valid name.
     */
    @Test
    public void shouldCheckIfStopNameValid() {
        assertDoesNotThrow(() -> {
            stopService.checkIfStopNameValid("Cardinal");
        });
    }

    /*
     * Test checkIfStopNameValid with empty name.
     */
    @Test
    public void shouldcheckIfStopNameValidWithNoName(){
        assertThrows(IllegalArgumentException.class, () -> {
            stopService.checkIfStopNameValid("");
        });
    }

    /*
     * Test checkStopLocNamingConvention with valid name.
     */
    @Test
    public void shouldCheckStopLocNamingConvention() {
        assertDoesNotThrow(() -> {
            stopService.checkStopLocNamingConvention("Dundee");
        });
    }

    /*
     * Test checkStopLocNamingConvention with invalid location.
     */
    @Test
    public void shouldCheckStopLocNamingConventionWithInvalidLoc(){
        assertThrows(IllegalArgumentException.class, () -> {
            stopService.checkStopLocNamingConvention("Dun-dee!");
        });
    }

    /*
     * Test checkStopLocNamingConvention with empty location.
     */
    @Test
    public void shouldCheckStopLocNamingConventionWithEmptyLoc(){
        assertThrows(IllegalArgumentException.class, () -> {
            stopService.checkStopLocNamingConvention("");
        });
    }

    /*
     * Test checkIfScheduleIdentifierExists with no scheduleIdentifier.
     */
    @Test
    public void shouldCheckIfScheduleIdentifierExists() {
        assertDoesNotThrow(() -> {
            stopService.checkIfScheduleIdentifierExists("R1");
        });
    }

    /*
     * Test checkIfScheduleIdentifierExists with existing scheduleIdentifier.
     */
    @Test
    public void shouldCheckIfScheduleIdentifierExistsWithExistingSI(){
        stopService.addStop("R1", "Route1", "65ID", "Cardinal", "Dundee", "07/03/2023", "Tuesday", "13:00", "16:00");
        
        assertThrows(IllegalArgumentException.class, () -> {
            stopService.checkIfScheduleIdentifierExists("R1");
        });
    }
}
