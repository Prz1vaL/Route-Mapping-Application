package com.stacs.routemappingapp.model.route;

import java.io.Serializable;

public class Route implements Serializable {
    private final String uniqueRouteNumber;
    private final String routeName;

    private final String destination;
    private final String startingPoint;

    public Route(String uniqueRouteNumber, String routeName, String destination, String startingPoint) {
        this.uniqueRouteNumber = uniqueRouteNumber;
        this.routeName = routeName;
        this.destination = destination;
        this.startingPoint = startingPoint;
    }

    public String getUniqueRouteNumber() {
        return uniqueRouteNumber;
    }

    public String getRouteName() {
        return routeName;
    }

    public String getDestination() {
        return destination;
    }


    public String getStartingPoint() {
        return startingPoint;
    }


}
