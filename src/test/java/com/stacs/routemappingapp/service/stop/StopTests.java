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
            String stopNumber = "";
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
        String expectedDayOfWeek = "TUESDAY";
  
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
            stopService.isBeforeTime("13:00", "16:00");
        });
    }

    /*
     * Test isBeforeTime with depature after arrival time.
     */
    @Test
    public void shouldIsBeforeTimeWithInvalidTimes(){
        assertThrows(IllegalArgumentException.class, () -> {
            stopService.isBeforeTime("16:00", "13:00");
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
            stopService.checkIfScheduleIdentifierExists("65ID");
        });
    }

    /*
     * Test checkIfScheduleIdentifierExists with existing scheduleIdentifier.
     */
    @Test
    public void shouldCheckIfScheduleIdentifierExistsWithExistingSI(){
        stopService.addStop("R1", "Route1", "65ID", "Cardinal", "Dundee", "07/03/2023", "Tuesday", "13:00", "16:00");

        assertThrows(IllegalArgumentException.class, () -> {
            stopService.checkIfScheduleIdentifierExists("65ID");
        });
    }

    /*
     * Test viewAllStops successfully.
     */
    @Test
    public void shouldViewAllStops() {
        stopService.addStop("R1", "Route1", "65ID", "Cardinal", "Dundee", "07/03/2023", "Tuesday", "13:00", "16:00");

        assertEquals(1, stopService.viewAllStops().size());
    }

    /*
     * Test viewAllStops where stop does not exist.
     */
    @Test
    public void shouldViewAllStopsThrowsExeption(){       
        assertThrows(IllegalArgumentException.class, () -> {
            stopService.viewAllStops();
        });
    }

    /*
     * Test deleteStop successfully.
     */
    @Test
    public void shouldDeleteStop() {
        stopService.addStop("R1", "Route1", "65ID", "Cardinal", "Dundee", "07/03/2023", "Tuesday", "13:00", "16:00");

        assertDoesNotThrow(() -> {
            stopService.deleteStop("R1");
        });

        assertEquals(0, stopService.viewAllStops().size());
    }

    /*
     * Test deleteStop where stop does not exist.
     */
    @Test
    public void shouldDeleteStopWithStopNotExisting(){       
        assertThrows(IllegalArgumentException.class, () -> {
            stopService.deleteStop("R1");
        });
    }

    // /*
    //  * Test viewRoutesByStopTime with valid name and time.
    //  */
    // @Test
    // public void shouldViewRoutesByStopTime() {
    //     stopService.addStop("R1", "Route1", "65ID", "Cardinal", "Dundee", "07/03/2023", "Tuesday", "13:00", "16:00");
    //     Map<String, Stop> stops = new HashMap<>();

    //     assertDoesNotThrow(() -> {
    //         stops = stopService.viewRoutesByStopTime("Cardinal", "16:00");
    //     });

    //     assertEquals(1, stops.size());
    // }

    // /*
    //  * Test viewRoutesByStopTime with no stopName.
    //  */
    // @Test
    // public void shouldViewRoutesByStopTimeWithNoName(){
    //     assertThrows(IllegalArgumentException.class, () -> {
    //         stopService.viewRoutesByStopTime("", "16:00");
    //     });
    // }

    // /*
    //  * Test viewRoutesByStopTime with no time.
    //  */
    // @Test
    // public void shouldViewRoutesByStopTimeWithNoTime(){
    //     assertThrows(IllegalArgumentException.class, () -> {
    //         stopService.viewRoutesByStopTime("Cardinal", "");
    //     });
    // }

    // /*
    //  * Test viewRoutesByStopTimeDept with valid name and time.
    //  */
    // @Test
    // public void shouldViewRoutesByStopTimeDept() {
    //     stopService.addStop("R1", "Route1", "65ID", "Cardinal", "Dundee", "07/03/2023", "Tuesday", "13:00", "16:00");
    //     Map<String, Stop> stops = new HashMap<>();

    //     assertDoesNotThrow(() -> {
    //         stops = stopService.viewRoutesByStopTimeDept("Cardinal", "13:00");
    //     });

    //     assertEquals(1, stops.size());
    // }

    // /*
    //  * Test viewRoutesByStopTimeDept with no stopName.
    //  */
    // @Test
    // public void shouldViewRoutesByStopTimeDeptWithNoName(){
    //     assertThrows(IllegalArgumentException.class, () -> {
    //         stopService.viewRoutesByStopTimeDept("", "13:00");
    //     });
    // }

    // /*
    //  * Test viewRoutesByStopTimeDept with no time.
    //  */
    // @Test
    // public void shouldViewRoutesByStopTimeDeptWithNoTime(){
    //     assertThrows(IllegalArgumentException.class, () -> {
    //         stopService.viewRoutesByStopTimeDept("Cardinal", "");
    //     });
    // }

    /*
     * Test viewRoutesByStopDay with valid name and day.
     */
    @Test
    public void shouldViewRoutesByStopDay() {
        stopService.addStop("R1", "Route1", "65ID", "Cardinal", "Dundee", "07/03/2023", "Tuesday", "13:00", "16:00");
        Map<String, Stop> stops = new HashMap<>();

        assertDoesNotThrow(() -> {
            stops = stopService.viewRoutesByStopDay("Cardinal", "Tuesday");
        });

        assertEquals(1, stops.size());
    }

    /*
     * Test viewRoutesByStopDay with valid name and time,
     * but does not exist.
     */
    @Test
    public void shouldViewRoutesByStopDayFindsNothing() {
        stopService.addStop("R1", "Route1", "65ID", "Cardinal", "Dundee", "07/03/2023", "Tuesday", "13:00", "16:00");
        Map<String, Stop> stops = new HashMap<>();

        assertDoesNotThrow(() -> {
            stops = stopService.viewRoutesByStopDay("North Road", "Friday");
        });

        assertEquals(0, stops.size());
    }

    /*
     * Test viewRoutesByStopDay with no stopName.
     */
    @Test
    public void shouldViewRoutesByStopDayWithNoName(){
        assertThrows(IllegalArgumentException.class, () -> {
            stopService.viewRoutesByStopDay("", "Friday");
        });
    }

    /*
     * Test viewRoutesByStopDay with no time.
     */
    @Test
    public void shouldViewRoutesByStopDayWithNoDay(){
        assertThrows(IllegalArgumentException.class, () -> {
            stopService.viewRoutesByStopDay("Cardinal", "");
        });
    }

    /*
     * Test checkIfStopExists with valid name.
     */
    @Test
    public void shouldCheckIfStopExists() {
        stopService.addStop("R1", "Route1", "65ID", "Cardinal", "Dundee", "07/03/2023", "Tuesday", "13:00", "16:00");

        assertDoesNotThrow(() -> {
            stopService.checkIfStopExists("Cardinal");
        });
    }

    /*
     * Test checkIfStopExists with no stopName.
     */
    @Test
    public void shouldCheckIfStopExistsWithNoName(){
        assertThrows(IllegalArgumentException.class, () -> {
            stopService.checkIfStopExists("");
        });
    }

    /*
     * Test checkIfStopExists where name does not exist.
     */
    @Test
    public void shouldCheckIfStopExistsFindsNothing(){
        stopService.addStop("R1", "Route1", "65ID", "Cardinal", "Dundee", "07/03/2023", "Tuesday", "13:00", "16:00");

        assertThrows(IllegalArgumentException.class, () -> {
            stopService.checkIfStopExists("North Road");
        });
    }

    /*
     * Test checkIfStopExists where time is inside arrival and depart.
     */
    @Test
    public void checkIfStopExistsByTime() {
        stopService.addStop("R1", "Route1", "65ID", "Cardinal", "Dundee", "07/03/2023", "Tuesday", "13:00", "16:00");
        Map<String,Stop> stops = new HashMap<>();

        assertDoesNotThrow(() -> {
            stops = stopService.ifStopExistsbyTime("Cardinal", "14:30");
        });

        assertEquals(1, stops.size());
    }

    /*
     * Test ifStopExistsbyTime with no stopName.
     */
    @Test
    public void shouldCheckIfStopExistsByTimeWithNoName(){
        assertThrows(IllegalArgumentException.class, () -> {
            stopService.ifStopExistsbyTime("", "");
        });
    }

    /*
     * Test checkIfStopExists where time is outside arrival and depart.
     */
    @Test
    public void shouldCheckIfStopExistsByTimeFindsNothing(){
        stopService.addStop("R1", "Route1", "65ID", "Cardinal", "Dundee", "07/03/2023", "Tuesday", "13:00", "16:00");

        assertThrows(IllegalArgumentException.class, () -> {
            stopService.ifStopExistsbyTime("Cardinal", "11:00");
        });
    }
}