package com.stacs.routemappingapp.model.route;

import java.io.Serializable;

/**
 * Model class for Route.
 * This class is used to store the information of a route.
 * The class implements Serializable to allow the object to be passed between activities.
 * The class has the following attributes:
 * uniqueRouteNumber: The unique route number of the route.
 * routeName: The name of the route.
 * destination: The destination of the route.
 * startingPoint: The starting point of the route.
 * The class has the following methods:
 * getUniqueRouteNumber: Returns the unique route number of the route.
 * getRouteName: Returns the name of the route.
 * getDestination: Returns the destination of the route.
 * getStartingPoint: Returns the starting point of the route.
 */
public class Route implements Serializable {
    private final String uniqueRouteNumber;
    private final String routeName;

    private final String destination;
    private final String startingPoint;

    /**
     * @param uniqueRouteNumber The unique route number of the route.
     * @param routeName         The name of the route.
     * @param destination       The destination of the route.
     * @param startingPoint     The starting point of the route.
     */
    public Route(String uniqueRouteNumber, String routeName, String destination, String startingPoint) {
        this.uniqueRouteNumber = uniqueRouteNumber;
        this.routeName = routeName;
        this.destination = destination;
        this.startingPoint = startingPoint;
    }

    /**
     * @return The unique route number of the route.
     */
    public String getUniqueRouteNumber() {
        return uniqueRouteNumber;
    }

    /**
     * @return The name of the route.
     */
    public String getRouteName() {
        return routeName;
    }

    /**
     * @return The destination of the route.
     */
    public String getDestination() {
        return destination;
    }


    /**
     * @return The starting point of the route.
     */
    public String getStartingPoint() {
        return startingPoint;
    }


}
