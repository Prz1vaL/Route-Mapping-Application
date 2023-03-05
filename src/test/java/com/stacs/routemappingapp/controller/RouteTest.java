package com.stacs.routemappingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stacs.routemappingapp.model.route.Route;
import com.stacs.routemappingapp.service.RouteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RouteTest {

    private MockMvc mockMvc;

    @Mock
    private RouteService routeService;

    @InjectMocks
    private RouteController routeController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(routeController).build();
    }

    @Test
    void shouldReturnListOfRoutes() throws Exception {
        Route route1 = new Route("123", "Route1", "Destination1", "StartingPoint1");
        Route route2 = new Route("456", "Route2", "Destination2", "StartingPoint2");
        List<Route> routeList = Arrays.asList(route1, route2);
        when(routeService.getRoutes()).thenReturn(routeList);

        // Perform the GET request and check values
        mockMvc.perform(MockMvcRequestBuilders.get("/routes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].uniqueRouteNumber").value("123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].routeName").value("Route1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].destination").value("Destination1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].startingPoint").value("StartingPoint1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].uniqueRouteNumber").value("456"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].routeName").value("Route2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].destination").value("Destination2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].startingPoint").value("StartingPoint2"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void shouldReturnEmptyListOfRoutes() throws Exception {
        when(routeService.getRoutes()).thenReturn(Collections.emptyList());

        // Perform the GET request and check values
        mockMvc.perform(MockMvcRequestBuilders.get("/routes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void shouldAddRoute() throws Exception {
        Map<String, String> data = new HashMap<>();
        data.put("uniqueRouteNumber", "123");
        data.put("routeName", "Route1");
        data.put("destination", "Destination1");
        data.put("startingPoint", "StartingPoint1");

        ArgumentCaptor<String> uniqueRouteNumberCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> routeNameCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> destinationCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> startingPointCaptor = ArgumentCaptor.forClass(String.class);


        doNothing().when(routeService).addRoute(uniqueRouteNumberCaptor.capture(),
            routeNameCaptor.capture(), destinationCaptor.capture(), startingPointCaptor.capture());

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/routes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(data)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        // Verify that the addRoute method was called with the correct arguments
        assertEquals("123", uniqueRouteNumberCaptor.getValue());
        assertEquals("Route1", routeNameCaptor.getValue());
        assertEquals("Destination1", destinationCaptor.getValue());
        assertEquals("StartingPoint1", startingPointCaptor.getValue());
    }

    // @Test
    // void shouldTestForDuplicateInput() throws Exception {
    //     // Set up test data
    //     Map<String, String> data = new HashMap<>();
    //     data.put("uniqueRouteNumber", "r123");
    //     data.put("routeName", "Route 1");
    //     data.put("destination", "Destination1");
    //     data.put("startingPoint", "StartingPoint1");
    
    //     // Stub to throw an exception for duplicates
    //     doNothing().when(routeService).addRoute("r123", "Route 1", "Destination1", "StartingPoint1");
        
    //     assertThrows(Exception.class, () -> {
    //     // Perform the POST request with valid data
    //     mockMvc.perform(MockMvcRequestBuilders.post("/routes")
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .content(new ObjectMapper().writeValueAsString(data)))
    //             .andExpect(MockMvcResultMatchers.status().isOk())
    //             .andDo(MockMvcResultHandlers.print());
    //     });
    // }


    @Test
    void shouldDeleteRoute() throws Exception {
        Route route = new Route("123", "Route 1", "Destination 1", "Starting Point 1");
        
        // Perform the DELETE request
        mockMvc.perform(MockMvcRequestBuilders.delete("/routes/{uniqueRouteNumber}", route.getUniqueRouteNumber())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    // @Test
    // void shouldReturnNotFoundForInvalidRouteNumber() throws Exception {
    //     String invalidRouteNumber = "999";

    //     // Perform the DELETE request with an invalid route number
    //     mockMvc.perform(MockMvcRequestBuilders.delete("/routes/{uniqueRouteNumber}", invalidRouteNumber)
    //             .contentType(MediaType.APPLICATION_JSON))
    //             .andExpect(MockMvcResultMatchers.status().isNotFound())
    //             .andDo(MockMvcResultHandlers.print());
    // }
}
