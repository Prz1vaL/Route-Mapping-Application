package com.stacs.routemappingapp.service;


import com.stacs.routemappingapp.model.route.Route;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class acts as a service and is used to store the data and perform the business logic of routes.
 * It is also used to save and load the data from the file.
 */
@Service
public class RouteService implements Serializable {

    private static Map<String, Route> route = new HashMap<>();

    /**
     * This method is used for checking if the Name is alphanumeric.
     *
     * @param name The name of the route.
     */
    public static void checkAlphaNumeric(String name) {
        if (name.matches("[a-zA-Z0-9]+")) {

        } else {
            throw new IllegalArgumentException("ID is not valid. \n");
        }
    }

    /**
     * This method is used for checking if the Name is in the correct format.
     *
     * @param name The name of the route.
     */
    public static void checkNamingConvention(String name) {
        if (name.matches("[a-zA-Z]+")) {

        } else {
            throw new IllegalArgumentException(" Name is not valid. \n");
        }

    }

    /**
     * This method saves the data to the file.
     *
     * @throws IOException If the file is not found.
     */
    public static void saveAppData() throws IOException {
        FileOutputStream f = new FileOutputStream("src/main/resources/data/route.ser");
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(route);
        o.close();
        f.close();
    }

    /**
     * This method loads the data from the file.
     *
     * @throws IOException If the file is not found.
     */
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
     * This method is used for checking if the ID is valid.
     *
     * @param uniqueRouteNumber Route ID.
     */
    public void checkIfIDValid(String uniqueRouteNumber) {
        if (uniqueRouteNumber.isEmpty()) {
            throw new IllegalArgumentException("No Route ID is given. \n");
        }
    }

    /**
     * This method is used for checking if the Name is valid.
     *
     * @param routeName The name of the route.
     */
    public void checkIfNameValid(String routeName) {
        if (routeName.isEmpty() || routeName.isBlank()) {
            throw new IllegalArgumentException("No Route Name is given. \n");
        }
    }

    /**
     * This method is used for adding a route.
     *
     * @param uniqueRouteNumber Route ID.
     * @param routeName         The name of the route.
     * @param destination       The destination of the route.
     * @param startingPoint     The starting point of the route.
     */
    public void addRoute(String uniqueRouteNumber, String routeName, String destination, String startingPoint) {
        if (route.containsKey(uniqueRouteNumber.toLowerCase())) {
            throw new IllegalArgumentException("Route ID already exists. \n");
        } else {
            route.put(uniqueRouteNumber.toLowerCase(), new Route(uniqueRouteNumber.toLowerCase(), routeName.toLowerCase(), destination.toLowerCase(), startingPoint.toLowerCase()));
        }
    }


    /**
     * This method is used for deleting a route.
     *
     * @param uniqueRouteNumber Route ID.
     */
    public void deleteRoute(String uniqueRouteNumber) {
        if (route.containsKey(uniqueRouteNumber)) {
            route.remove(uniqueRouteNumber);
        } else {
            throw new IllegalArgumentException("Route ID does not exist. \n");
        }
    }

    /**
     * This method is used for viewing all the routes.
     *
     * @return Returns a list of all the routes.
     */
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


    /**
     * This method is used for checking if the route ID and route name matches.
     *
     * @param uniqueRouteNumber Route ID.
     * @param routeName         The name of the route.
     */
    public void checkIfRouteNameIDMatch(String uniqueRouteNumber, String routeName) {
        if (route.containsKey(uniqueRouteNumber.toLowerCase())) {
            if (routeName.toLowerCase().equals(route.get(uniqueRouteNumber.toLowerCase()).getRouteName())) {
                System.out.println("Route ID and Route Name matches. \n");
            } else {
                throw new IllegalArgumentException("Route ID and Route Name does not match. \n");
            }
        } else {
            throw new IllegalArgumentException("Route ID does not exist. \n");
        }
    }

    /**
     * This method is used for getting the route information by route ID.
     *
     * @param routeIdentifier Route ID.
     * @return Returns the route information.
     */
    public Map<String, Route> getRouteInfoByStopName(String routeIdentifier) {
        Map<String, Route> routeInfoByStopName = new HashMap<>();
        if (route.isEmpty()) {
            throw new IllegalArgumentException("No routes exists in the system. \n");
        } else if (!route.isEmpty()) {
            for (Map.Entry<String, Route> entry : route.entrySet()) {
                if (routeIdentifier.toLowerCase().equals(entry.getValue().getRouteName())) {
                    routeInfoByStopName.put(entry.getKey(), entry.getValue());
                }
            }
        } else {
            throw new IllegalArgumentException("Enter proper route name. \n");
        }
        return routeInfoByStopName;
    }    //

    /**
     * This method is used for obtaining all the routes.
     *
     * @return Returns a list of all the routes.
     * Used in front end development.
     */
    //Add by Li
    public List<Route> getRoutes() {
        return new ArrayList<>(route.values());
    }

    /**
     * This method wipes all the routes.
     * Used for testing purposes.
     */
    public void wipeRoutes() {
        route.clear();
    }
}
