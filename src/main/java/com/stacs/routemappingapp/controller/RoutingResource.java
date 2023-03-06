package com.stacs.routemappingapp.controller;

import com.stacs.routemappingapp.model.route.Route;
import com.stacs.routemappingapp.model.stop.Stop;
import com.stacs.routemappingapp.service.RouteService;
import com.stacs.routemappingapp.service.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    public void checkIfIDValid(String uniqueRouteNumber) {
        routeService.checkIfIDValid(uniqueRouteNumber);
    }


    public void checkIfNameValid(String routeName) {
        routeService.checkIfNameValid(routeName);
    }

    public void checkIfStopIDValid(String stopNumber) {
        stopService.checkIfStopIDValid(stopNumber);
    }

    public void addRoute(String uniqueRouteNumber, String routeName, String destination, String startingPoint) {
        routeService.addRoute(uniqueRouteNumber, routeName, destination, startingPoint);
    }

    public Map<String, Route> callAllRoutes() {
        return routeService.callAllRoutes();
    }


    public void loadAppData() {
        RouteService.loadAppData();
        StopService.loadAppData();

    }

    public void saveAppData() throws IOException {
        RouteService.saveAppData();
        StopService.saveAppData();

    }


    public void addStop(String uniqueRouteNumber, String routeName, String stopName, String scheduleIdentifier, String stopLocation, String date, String day, String departureTime, String arrivalTime) {
        stopService.addStop(uniqueRouteNumber, routeName, stopName, scheduleIdentifier, stopLocation, date, day, departureTime, arrivalTime);
    }

    public HashMap<String, Stop> viewRoutesByStop(String stopName) {
        return stopService.viewRoutesByStop(stopName);
    }

    public void checkAlphaNumeric(String name) {
        RouteService.checkAlphaNumeric(name);
    }

    public void checkNamingConvention(String name) {
        RouteService.checkNamingConvention(name);
    }

    public void deleteRoute(String uniqueRouteNumber) {
        routeService.deleteRoute(uniqueRouteNumber);
    }

    public void checkIfRouteNameIDMatch(String uniqueRouteNumber, String routeName) {
        routeService.checkIfRouteNameIDMatch(uniqueRouteNumber, routeName);
    }


    public String getDayOfWeek(String date) {
        return stopService.getDayOfWeek(date);
    }

    public void checkIfDateValid(String date) {
        stopService.checkIfDateValid(date);
    }

    public void checkIfTimeValid(String time) {
        stopService.checkIfTimeValid(time);
    }

    public void checkStopNamingConvention(String name) {
        stopService.checkStopNamingConvention(name);
    }

    public void checkIfScheduleIdentifierValid(String scheduleIdentifier) {
        stopService.checkIfScheduleIdentifierValid(scheduleIdentifier);
    }

    public void isBeforeTime(String departureTime, String arrivalTime) {
        stopService.isBeforeTime(departureTime, arrivalTime);
    }

    public void checkIfStopNameValid(String stopName) {
        stopService.checkIfStopNameValid(stopName);
    }

    public void checkStopLocNamingConvention(String stopLocation) {
        stopService.checkStopLocNamingConvention(stopLocation);
    }

    public void checkIfScheduleIdentifierExists(String scheduleIdentifier) {
        stopService.checkIfScheduleIdentifierExists(scheduleIdentifier);
    }

    public void deleteStop(String scheduleIdentifier) {
        stopService.deleteStop(scheduleIdentifier);
    }

    public HashMap<String, Stop> viewAllStops() {
        return stopService.viewAllStops();
    }

    public Map<String, Route> getRouteInfoByStopName(String routeIdentifier) {
        return routeService.getRouteInfoByStopName(routeIdentifier);

    }


    public Map<String, Stop> viewRoutesByStopDay(String stopName, String day) {
        return stopService.viewRoutesByStopDay(stopName, day);
    }

    public void checkIfStopExists(String stopName) {
        stopService.checkIfStopExists(stopName);
    }

    public Map<String,Stop> ifStopExistsbyTime(String stopName, String time) {
        return stopService.ifStopExistsbyTime(stopName, time);
    }
}
