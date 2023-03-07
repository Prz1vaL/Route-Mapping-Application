package com.stacs.routemappingapp.api;

import com.stacs.routemappingapp.model.stop.Stop;
import com.stacs.routemappingapp.service.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class acts as a controller and is used to route the requests to the appropriate service.
 * Used for the front end to communicate with the back end.
 */
@CrossOrigin
@RestController
@RequestMapping("/stops")
public class StopController {

    @Autowired
    private StopService stopService = new StopService();

    /**
     * @return The list of all stops.
     */
    @GetMapping
    public List<Stop> getAllStops() {
        return stopService.getStops();
    }

    /**
     * @param data The data of the stop.
     */
    @PostMapping
    public void addStop(@RequestBody Map<String, String> data) {

        String uniqueRouteNumber = data.get("uniqueRouteNumber");
        String routeName = data.get("routeName");
        String scheduleIdentifier = data.get("scheduleIdentifier");
        String stopName = data.get("stopName");
        String stopLocation = data.get("stopLocation");
        String date = data.get("date");
        String day = data.get("day");
        String departureTime = data.get("departureTime");
        String arrivalTime = data.get("arrivalTime");
        stopService.addStop(uniqueRouteNumber, routeName, scheduleIdentifier, stopName, stopLocation, date, day, departureTime, arrivalTime);
    }

    /**
     * @param scheduleIdentifier The schedule identifier of the stop.
     */
    @DeleteMapping("/{scheduleIdentifier}")
    public void deleteStop(@PathVariable String scheduleIdentifier) {
        stopService.deleteStop(scheduleIdentifier);
    }

    /**
     * @param stopName The name of the stop.
     * @return The list of all stops with the same name.
     */
    @GetMapping("/{stopName}/routes")
    public HashMap<String, Stop> viewRoutesByStop(@PathVariable String stopName) {
        return stopService.viewRoutesByStop(stopName);
    }

    /**
     * @param stopName The name of the stop.
     * @param day      The day of the stop.
     * @return The list of all stops with the same name and day.
     */
    @GetMapping("/{stopName}/{day}/routes")
    public Map<String, Stop> viewRoutesByStopDay(@PathVariable("stopName") String stopName, @PathVariable("day") String day) {
        return stopService.viewRoutesByStopDay(stopName, day);
    }

    /**
     * @param stopName The name of the stop.
     * @param time     The time of the stop.
     * @return The list of all stops with the same name and time.
     */
    @GetMapping("/routes/{stopName}/{time}")
    public Map<String, Stop> ifStopExistsbyTime(@PathVariable("stopName") String stopName, @PathVariable("time") String time) {
        return stopService.ifStopExistsbyTime(stopName, time);
    }

    /**
     * @param date The date of the stop.
     * @return The day of the stop.
     */
    @GetMapping("/{date}")
    public String getDayOfWeek(@PathVariable("date") String date) {
        return stopService.getDayOfWeek(date);
    }
}
