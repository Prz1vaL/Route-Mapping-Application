package com.stacs.routemappingapp.model.stop;

import java.io.Serializable;

/**
 * Model class for Stop.
 */
public class Stop implements Serializable {
    private final String RouteNumber;
    private final String RouteName;
    private final String scheduleIdentifier;
    private final String stopName;

    private final String stopLocation;
    private final String date;
    private final String day;
    private final String departureTime;
    private final String arrivalTime;


    public Stop(String RouteNumber, String RouteName, String scheduleIdentifier, String stopName, String stopLocation, String date, String day, String departureTime, String arrivalTime) {
        this.RouteNumber = RouteNumber;
        this.RouteName = RouteName;
        this.scheduleIdentifier = scheduleIdentifier;
        this.stopName = stopName;
        this.stopLocation = stopLocation;
        this.date = date;
        this.day = day;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getScheduleIdentifier() {
        return scheduleIdentifier;
    }

    public String getStopName() {
        return stopName;
    }

    public String getRouteNumber() {
        return RouteNumber;
    }

    public String getRouteName() {
        return RouteName;
    }


    public String getStopLocation() {
        return stopLocation;
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }
}
