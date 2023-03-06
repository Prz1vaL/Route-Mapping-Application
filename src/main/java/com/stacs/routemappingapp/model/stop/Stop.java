package com.stacs.routemappingapp.model.stop;

import java.io.Serializable;

/**
 * Model class for Stop.
 * This class is used to store the information of a stop.
 * The class implements Serializable to allow the object to be passed between activities.
 * The class has the following attributes:
 * uniqueRouteNumber: The route number of the route that the stop is on.
 * RouteName: The name of the route that the stop is on.
 * scheduleIdentifier: The schedule identifier of the stop.
 * stopName: The name of the stop.
 * stopLocation: The location of the stop.
 * date: The date of the stop.
 * day: The day of the stop.
 * departureTime: The departure time of the stop.
 * arrivalTime: The arrival time of the stop.
 * The class has the following methods:
 * getUniqueRouteNumber: Returns the route number of the route that the stop is on.
 * getRouteName: Returns the name of the route that the stop is on.
 * getScheduleIdentifier: Returns the schedule identifier of the stop.
 * getStopName: Returns the name of the stop.
 * getStopLocation: Returns the location of the stop.
 * getDate: Returns the date of the stop.
 * getDay: Returns the day of the stop.
 * getDepartureTime: Returns the departure time of the stop.
 * getArrivalTime: Returns the arrival time of the stop.
 */
public class Stop implements Serializable {
    private final String uniqueRouteNumber;
    private final String RouteName;
    private final String scheduleIdentifier;
    private final String stopName;

    private final String stopLocation;
    private final String date;
    private final String day;
    private final String departureTime;
    private final String arrivalTime;


    public Stop(String uniqueRouteNumber, String RouteName, String scheduleIdentifier, String stopName, String stopLocation, String date, String day, String departureTime, String arrivalTime) {
        this.uniqueRouteNumber = uniqueRouteNumber;
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

    public String getUniqueRouteNumber() {
        return uniqueRouteNumber;
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
