package com.stacs.routemappingapp.api;

import com.stacs.routemappingapp.service.StopService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class acts as a controller and is used to route the requests to the appropriate service.
 */
@RestController
@CrossOrigin
@RequestMapping("/data")
public class StopDataController {

    /**
     * @return The list of all routes.
     */
    @GetMapping("/stop/load")
    public void loadStop() {
        StopService.loadAppData();
    }

    /**
     * @return The list of all routes.
     */
    @GetMapping("/stop/save")
    public String saveStop() {
        try {
            StopService.saveAppData();
        } catch (Exception e) {
            return "Failure!";
        }
        return "Successful!";
    }
}
