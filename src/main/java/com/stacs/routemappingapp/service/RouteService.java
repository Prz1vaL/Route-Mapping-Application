package com.stacs.routemappingapp.service;

import com.stacs.routemappingapp.model.route.Route;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RouteService implements Serializable {

    private static Map<String, Route> route = new HashMap<>();

    public static void checkAlphaNumeric(String name) {
        if (name.matches("[a-zA-Z0-9]+")) {

        } else {
            throw new IllegalArgumentException("Route ID is not valid. \n");
        }
    }

    public static void checkNamingConvention(String name) {
        if (name.matches("[a-zA-Z]+")) {

        } else {
            throw new IllegalArgumentException("Route Name is not valid. \n");
        }

    }

    public static void saveAppData() throws IOException {
        FileOutputStream f = new FileOutputStream("src/main/resources/data/route.ser");
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(route);
        o.close();
        f.close();
    }

    public static void loadAppData() {
        try {
            FileInputStream fi = new FileInputStream(new File("src/main/resources/data/route.ser"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            //Read objects
            route = (Map<String, Route>) oi.readObject();
            oi.close();
            fi.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param uniqueRouteNumber Route ID.
     */
    public void checkIfIDValid(String uniqueRouteNumber) {
        if (uniqueRouteNumber.isEmpty()) {
            throw new IllegalArgumentException("No Route ID is given. \n");
        }
    }

    public void checkIfNameValid(String routeName) {
        if (routeName.isEmpty() || routeName.isBlank()) {
            throw new IllegalArgumentException("No Route Name is given. \n");
        }

    }

    public void addRoute(String uniqueRouteNumber, String routeName, String destination, String startingPoint) {
        if (route.containsKey(uniqueRouteNumber.toLowerCase())) {
            throw new IllegalArgumentException("Route ID already exists. \n");
        } else {
            //  Route newRoute = new Route(uniqueRouteNumber, routeName, destination, startingPoint);
            route.put(uniqueRouteNumber, new Route(uniqueRouteNumber.toLowerCase(), routeName.toLowerCase(), destination.toLowerCase(), startingPoint.toLowerCase()));
        }
    }


    public void deleteRoute(String uniqueRouteNumber) {
        if (route.containsKey(uniqueRouteNumber)) {
            route.remove(uniqueRouteNumber);
        } else {
            throw new IllegalArgumentException("Route ID does not exist. \n");
        }
    }

    public HashMap<String, Route> callAllRoutes() {
        HashMap<String, Route> viewAllRoutes = new HashMap<>();
        if (route.isEmpty()) {
            throw new IllegalArgumentException("No routes exists in the system. \n");
        } else {
            for (Map.Entry<String, Route> entry : route.entrySet()) {
                viewAllRoutes.put(entry.getKey(), entry.getValue());
            }
        }

        return viewAllRoutes;

    }


    public void checkIfRouteNameIDMatch(String uniqueRouteNumber, String routeName) {
        if (!route.isEmpty()) {
            for (Map.Entry<String, Route> entry : route.entrySet()) {
                if (uniqueRouteNumber.toLowerCase().equals(entry.getKey()) && (routeName.toLowerCase().equals(entry.getValue().getRouteName()))) {
                    System.out.println("Route ID and Route Name matches. \n");
                    break;
                } else {
                    throw new IllegalArgumentException("Route ID and Route Name does not match. \n");
                }
            }

        } else {
            throw new IllegalArgumentException("No routes exists in the system. \n");
        }
    }


    public Map<String, Route> getRouteInfoByStopName(String routeIdentifier) {
        Map<String, Route> routeInfoByStopName = new HashMap<>();
        if(route.isEmpty()){
            throw new IllegalArgumentException("No routes exists in the system. \n");
        }  else if (!route.isEmpty()) {
            for (Map.Entry<String, Route> entry : route.entrySet()) {
                if (routeIdentifier.toLowerCase().equals(entry.getValue().getRouteName())) {
                    routeInfoByStopName.put(entry.getKey(), entry.getValue());
                }
            }
        } else {
            throw new IllegalArgumentException("Enter proper route name. \n");
        }
        return routeInfoByStopName;
    }

}
