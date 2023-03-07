package com.stacs.routemappingapp.api;

import com.stacs.routemappingapp.service.RouteService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class acts as a controller and is used to route the requests to the appropriate service.
 * Used for the front end to communicate with the back end.
 */
@RestController
@CrossOrigin
@RequestMapping("/data")
public class RouteDataController {

    /**
     * @return The list of all routes.
     */
    @GetMapping("/route/load")
    public void loadStop() {
        RouteService.loadAppData();
    }

    /**
     * @return The list of all routes.
     */
    @GetMapping("/route/save")
    public String saveStop() {
        try {
            RouteService.saveAppData();
        } catch (Exception e) {
            return "Failure!";
        }
        return "Successful!";
    }
}