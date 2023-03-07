package com.stacs.routemappingapp.api;

import com.stacs.routemappingapp.model.route.Route;
import com.stacs.routemappingapp.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * This class acts as a controller and is used to route the requests to the appropriate service.
 * Used for the front end to communicate with the back end.
 */
@CrossOrigin
@RestController
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    private RouteService routeService = new RouteService();

    /**
     * @return The list of all routes.
     */
    @GetMapping
    public List<Route> getAllRoutes() {
        return routeService.getRoutes();
    }

    /**
     * @param data The data of the route.
     */
    @PostMapping
    public void addRoute(@RequestBody Map<String, String> data) {
        String uniqueRouteNumber = data.get("uniqueRouteNumber");
        String routeName = data.get("routeName");
        String destination = data.get("destination");
        String startingPoint = data.get("startingPoint");
        routeService.addRoute(uniqueRouteNumber, routeName, destination, startingPoint);
    }

    /**
     * @param uniqueRouteNumber The unique route number of the route.
     */
    @DeleteMapping("/{uniqueRouteNumber}")
    public void deleteRoute(@PathVariable String uniqueRouteNumber) {
        routeService.deleteRoute(uniqueRouteNumber);
    }
}
