package com.stacs.routemappingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stacs.routemappingapp.model.stop.Stop;
import com.stacs.routemappingapp.service.StopService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasItems;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StopTest {

    private MockMvc mockMvc;

    @Mock
    private StopService stopService;

    @InjectMocks
    private StopController stopController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(stopController).build();
    }

    /*
     * Test to get all stops
     */
    @Test
    void shouldGetAllStops() throws Exception {
        Stop stop1 = new Stop("uniqueRouteNumber1", "routeName1", "scheduleIdentifier1", "stopName1", 
            "stopLocation1", "date1", "day1", "departureTime1", "arrivalTime1");
        Stop stop2 = new Stop("uniqueRouteNumber2", "routeName2", "scheduleIdentifier2", "stopName2",
            "stopLocation2", "date2", "day2", "departureTime2", "arrivalTime2");
        List<Stop> stops = Arrays.asList(stop1, stop2);
        when(stopService.getStops()).thenReturn(stops);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/stops")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].uniqueRouteNumber").value("uniqueRouteNumber1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].routeName").value("routeName1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].scheduleIdentifier").value("scheduleIdentifier1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].stopName").value("stopName1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].stopLocation").value("stopLocation1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].date").value("date1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].day").value("day1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departureTime").value("departureTime1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].arrivalTime").value("arrivalTime1"))

                .andExpect(MockMvcResultMatchers.jsonPath("$[1].uniqueRouteNumber").value("uniqueRouteNumber2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].routeName").value("routeName2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].scheduleIdentifier").value("scheduleIdentifier2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].stopName").value("stopName2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].stopLocation").value("stopLocation2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].date").value("date2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].day").value("day2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departureTime").value("departureTime2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].arrivalTime").value("arrivalTime2"))
                .andDo(MockMvcResultHandlers.print());
    }

    /*
     * Test to add Stop 
     */
    @Test
    void shouldAddStops() throws Exception {
        Map<String, String> data = new HashMap<>();
        data.put("uniqueRouteNumber", "uniqueRouteNumber1");
        data.put("routeName", "routeName1");
        data.put("scheduleIdentifier", "scheduleIdentifier1");
        data.put("stopName", "stopName1");
        data.put("stopLocation", "stopLocation1");
        data.put("date", "date1");
        data.put("day", "day1");
        data.put("departureTime", "departureTime1");
        data.put("arrivalTime", "arrivalTime1");

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/stops")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(data)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        // Verify that the addStop method was called with the correct arguments
        verify(stopService, times(1)).addStop("uniqueRouteNumber1", "routeName1", "scheduleIdentifier1",
             "stopName1", "stopLocation1", "date1", "day1", "departureTime1", "arrivalTime1");
    }

    /*
     * Test to delete stop
     */
    @Test
    void shouldDeleteStop() throws Exception {
        Stop stop = new Stop("uniqueRouteNumber1", "routeName1", "scheduleIdentifier1", "stopName1", 
            "stopLocation1", "date1", "day1", "departureTime1", "arrivalTime1");
        
        // Perform the DELETE request
        mockMvc.perform(MockMvcRequestBuilders.delete("/stops/{scheduleIdentifier}", stop.getScheduleIdentifier())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        // Verify that the deleteStop method was called with the correct arguments
        verify(stopService, times(1)).deleteStop("scheduleIdentifier1");
    }

    /*
     * Test to view all routes by stop name
     */
    @Test
    void shouldViewRoutesByStop() throws Exception {
        HashMap<String, Stop> routesByStop = new HashMap<>();
        Stop stop1 = new Stop("uniqueRouteNumber1", "routeName1", "scheduleIdentifier1", "stopName1", 
            "stopLocation1", "date1", "day1", "departureTime1", "arrivalTime1");
        Stop stop2 = new Stop("uniqueRouteNumber2", "routeName2", "scheduleIdentifier2", "stopName1",
            "stopLocation2", "date2", "day2", "departureTime2", "arrivalTime2");
        routesByStop.put("scheduleIdentifier1", stop1);
        routesByStop.put("scheduleIdentifier2", stop2);

        when(stopService.viewRoutesByStop("stopName1")).thenReturn(routesByStop);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/stops/" + "stopName1" + "/routes")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].uniqueRouteNumber", hasItems("uniqueRouteNumber1", "uniqueRouteNumber2")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].routeName", hasItems("routeName1", "routeName2")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].scheduleIdentifier", hasItems("scheduleIdentifier1", "scheduleIdentifier2")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].stopName", hasItems("stopName1", "stopName1")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].stopLocation", hasItems("stopLocation1", "stopLocation2")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].date", hasItems("date1", "date2")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].day", hasItems("day1", "day2")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].departureTime", hasItems("departureTime1", "departureTime2")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].arrivalTime", hasItems("arrivalTime1", "arrivalTime2")));

        // Verify that the viewRoutesByStop method was called with the correct arguments
        verify(stopService, times(1)).viewRoutesByStop("stopName1");
    }

    /*
     * Test to view all routes by name and day
     */
    @Test
    void shouldViewRoutesByStopDay() throws Exception {
        HashMap<String, Stop> routesByDay = new HashMap<>();
        Stop stop1 = new Stop("uniqueRouteNumber1", "routeName1", "scheduleIdentifier1", "stopName1", 
            "stopLocation1", "date1", "day1", "departureTime1", "arrivalTime1");
        Stop stop2 = new Stop("uniqueRouteNumber2", "routeName2", "scheduleIdentifier2", "stopName1",
            "stopLocation2", "date2", "day1", "departureTime2", "arrivalTime2");
        routesByDay.put("scheduleIdentifier1", stop1);
        routesByDay.put("scheduleIdentifier2", stop2);

        when(stopService.viewRoutesByStopDay("stopName1", "day1")).thenReturn(routesByDay);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/stops/" + "stopName1" + '/' + "day1" + "/routes")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].routeNumber", hasItems("uniqueRouteNumber1", "uniqueRouteNumber2")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].routeName", hasItems("routeName1", "routeName2")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].scheduleIdentifier", hasItems("scheduleIdentifier1", "scheduleIdentifier2")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].stopName", hasItems("stopName1", "stopName1")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].stopLocation", hasItems("stopLocation1", "stopLocation2")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].date", hasItems("date1", "date2")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].day", hasItems("day1", "day1")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].departureTime", hasItems("departureTime1", "departureTime2")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].arrivalTime", hasItems("arrivalTime1", "arrivalTime2")));

        // Verify that the viewRoutesByStopDay method was called with the correct arguments
        verify(stopService, times(1)).viewRoutesByStopDay("stopName1", "day1");
    }

    /*
     * Test to get all routes with a specified stop name 
     * and that exist at the specified time 
     */
    @Test
    void shouldGetIfStopExistsbyTime() throws Exception {
        HashMap<String, Stop> routesByTime = new HashMap<>();
        Stop stop1 = new Stop("uniqueRouteNumber1", "routeName1", "scheduleIdentifier1", "stopName1", 
            "stopLocation1", "date1", "day1", "13:00", "16:00");
        Stop stop2 = new Stop("uniqueRouteNumber2", "routeName2", "scheduleIdentifier2", "stopName1",
            "stopLocation2", "date2", "day2", "12:00", "14:00");
        routesByTime.put("scheduleIdentifier1", stop1);
        routesByTime.put("scheduleIdentifier2", stop2);

        when(stopService.ifStopExistsbyTime("stopName1", "13:00")).thenReturn(routesByTime);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/stops" + "/routes" + '/' + "stopName1" + '/' + "13:00")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].uniqueRouteNumber1", hasItems("uniqueRouteNumber1", "uniqueRouteNumber2")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].routeName", hasItems("routeName1", "routeName2")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].scheduleIdentifier", hasItems("scheduleIdentifier1", "scheduleIdentifier2")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].stopName", hasItems("stopName1", "stopName1")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].stopLocation", hasItems("stopLocation1", "stopLocation2")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].date", hasItems("date1", "date2")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].day", hasItems("day1", "day2")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].departureTime", hasItems("13:00", "12:00")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].arrivalTime", hasItems("16:00", "14:00")));

        // Verify that the ifStopExistsbyTime method was called with the correct arguments
        verify(stopService, times(1)).ifStopExistsbyTime("stopName1", "13:00");
    }
}
