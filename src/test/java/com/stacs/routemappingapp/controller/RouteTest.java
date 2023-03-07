
package com.stacs.routemappingapp.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stacs.routemappingapp.model.route.Route;
import com.stacs.routemappingapp.service.RouteService;
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

import java.util.*;

import static org.mockito.Mockito.*;

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

    /*
     * Test to get routes
     */
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

    /*
     * Test to get route but empty
     */
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

    /*
     * Test to add route
     */
    @Test
    void shouldAddRoute() throws Exception {
        Map<String, String> data = new HashMap<>();
        data.put("uniqueRouteNumber", "123");
        data.put("routeName", "Route1");
        data.put("destination", "Destination1");
        data.put("startingPoint", "StartingPoint1");

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/routes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(data)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        // Verify that the addRoute method was called with the correct arguments
        verify(routeService, times(1)).addRoute("123", "Route1",
            "Destination1", "StartingPoint1");
    }


    /*
     * Test to delete route
     */
    @Test
    void shouldDeleteRoute() throws Exception {
        Route route = new Route("123", "Route 1", "Destination 1", "Starting Point 1");
        
        // Perform the DELETE request
        mockMvc.perform(MockMvcRequestBuilders.delete("/routes/{uniqueRouteNumber}", route.getUniqueRouteNumber())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        // Verify that the deleteRoute method was called with the correct arguments
        verify(routeService, times(1)).deleteRoute("123");
    }

}
