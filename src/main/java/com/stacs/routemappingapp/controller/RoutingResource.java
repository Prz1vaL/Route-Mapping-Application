package com.stacs.routemappingapp.controller;

import com.stacs.routemappingapp.model.route.Route;
import com.stacs.routemappingapp.model.stop.Stop;
import com.stacs.routemappingapp.service.RouteService;
import com.stacs.routemappingapp.service.StopService;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * This class acts as a controller and is used to route the requests to the appropriate service.
 */
public class RoutingResource implements Serializable {
    private RouteService routeService = new RouteService();
    private StopService stopService = new StopService();

    public RoutingResource() {
        this.routeService = routeService;
    }


    /**
     * @param uniqueRouteNumber The unique route number of the route.
     */
    public void checkIfIDValid(String uniqueRouteNumber) {
        routeService.checkIfIDValid(uniqueRouteNumber);
    }


    /**
     * @param routeName The name of the route.
     */
    public void checkIfNameValid(String routeName) {
        routeService.checkIfNameValid(routeName);
    }

    /**
     * @param stopNumber The unique stop number of the stop.
     */
    public void checkIfStopIDValid(String stopNumber) {
        stopService.checkIfStopIDValid(stopNumber);
    }

    /**
     * @param uniqueRouteNumber The unique route number of the route.
     * @param routeName         The name of the route.
     * @param destination       The destination of the route.
     * @param startingPoint     The starting point of the route.
     */
    public void addRoute(String uniqueRouteNumber, String routeName, String destination, String startingPoint) {
        routeService.addRoute(uniqueRouteNumber, routeName, destination, startingPoint);
    }

    /**
     * @return The list of all routes.
     */
    public Map<String, Route> callAllRoutes() {
        return routeService.callAllRoutes();
    }


    /**
     * Loads the data from the file.
     */
    public void loadAppData() {
        RouteService.loadAppData();
        StopService.loadAppData();

    }

    /**
     * Saves the data to the file.
     *
     * @throws IOException If an input or output exception occurred.
     */
    public void saveAppData() throws IOException {
        RouteService.saveAppData();
        StopService.saveAppData();

    }


    /**
     * @param uniqueRouteNumber  The unique route number of the route.
     * @param routeName          The name of the route.
     * @param stopName           The name of the stop.
     * @param scheduleIdentifier The schedule identifier of the stop.
     * @param stopLocation       The location of the stop.
     * @param date               The date of the stop.
     * @param day                The day of the stop.
     * @param departureTime      The departure time of the stop.
     * @param arrivalTime        The arrival time of the stop.
     */
    public void addStop(String uniqueRouteNumber, String routeName, String stopName, String scheduleIdentifier, String stopLocation, String date, String day, String departureTime, String arrivalTime) {
        stopService.addStop(uniqueRouteNumber, routeName, stopName, scheduleIdentifier, stopLocation, date, day, departureTime, arrivalTime);
    }

    /**
     * @param stopName The name of the stop.
     * @return The list of all stops.
     */
    public HashMap<String, Stop> viewRoutesByStop(String stopName) {
        return stopService.viewRoutesByStop(stopName);
    }

    /**
     * @param name The name of the route.
     */
    public void checkAlphaNumeric(String name) {
        RouteService.checkAlphaNumeric(name);
    }

    /**
     * @param name The name of the route.
     */
    public void checkNamingConvention(String name) {
        RouteService.checkNamingConvention(name);
    }

    /**
     * @param uniqueRouteNumber The unique route number of the route.
     */
    public void deleteRoute(String uniqueRouteNumber) {
        routeService.deleteRoute(uniqueRouteNumber);
    }

    /**
     * @param uniqueRouteNumber The unique route number of the route.
     * @param routeName         The name of the route.
     */
    public void checkIfRouteNameIDMatch(String uniqueRouteNumber, String routeName) {
        routeService.checkIfRouteNameIDMatch(uniqueRouteNumber, routeName);
    }


    /**
     * @param date The date of the stop.
     * @return The day of the stop.
     */
    public String getDayOfWeek(String date) {
        return stopService.getDayOfWeek(date);
    }

    /**
     * @param date The date of the stop.
     */
    public void checkIfDateValid(String date) {
        stopService.checkIfDateValid(date);
    }

    /**
     * @param time The time of the stop.
     */
    public void checkIfTimeValid(String time) {
        stopService.checkIfTimeValid(time);
    }

    /**
     * @param name The name of the route.
     */
    public void checkStopNamingConvention(String name) {
        stopService.checkStopNamingConvention(name);
    }

    /**
     * @param scheduleIdentifier The schedule identifier of the stop.
     */
    public void checkIfScheduleIdentifierValid(String scheduleIdentifier) {
        stopService.checkIfScheduleIdentifierValid(scheduleIdentifier);
    }

    /**
     * @param departureTime The departure time of the stop.
     * @param arrivalTime   The arrival time of the stop.
     */
    public void isBeforeTime(String departureTime, String arrivalTime) {
        stopService.isBeforeTime(departureTime, arrivalTime);
    }

    /**
     * @param stopName The name of the stop.
     */
    public void checkIfStopNameValid(String stopName) {
        stopService.checkIfStopNameValid(stopName);
    }

    /**
     * @param stopLocation The location of the stop.
     */
    public void checkStopLocNamingConvention(String stopLocation) {
        stopService.checkStopLocNamingConvention(stopLocation);
    }

    /**
     * @param scheduleIdentifier The schedule identifier of the stop.
     */
    public void checkIfScheduleIdentifierExists(String scheduleIdentifier) {
        stopService.checkIfScheduleIdentifierExists(scheduleIdentifier);
    }

    /**
     * @param scheduleIdentifier The schedule identifier of the stop.
     */
    public void deleteStop(String scheduleIdentifier) {
        stopService.deleteStop(scheduleIdentifier);
    }

    /**
     * @return The list of all stops.
     */
    public HashMap<String, Stop> viewAllStops() {
        return stopService.viewAllStops();
    }

    /**
     * @param routeIdentifier The unique route number of the route.
     * @return The list of all routes.
     */
    public Map<String, Route> getRouteInfoByStopName(String routeIdentifier) {
        return routeService.getRouteInfoByStopName(routeIdentifier);

    }


    /**
     * @param stopName The name of the stop.
     * @param day      The day of the stop.
     * @return The list of all stops.
     */
    public Map<String, Stop> viewRoutesByStopDay(String stopName, String day) {
        return stopService.viewRoutesByStopDay(stopName, day);
    }

    /**
     * @param stopName The name of the stop.
     */
    public void checkIfStopExists(String stopName) {
        stopService.checkIfStopExists(stopName);
    }

    /**
     * @param stopName The name of the stop.
     * @param time     The time of the stop.
     * @return The list of all stops.
     */
    public Map<String, Stop> ifStopExistsbyTime(String stopName, String time) {
        return stopService.ifStopExistsbyTime(stopName, time);
    }
}
