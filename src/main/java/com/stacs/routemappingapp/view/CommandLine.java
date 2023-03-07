package com.stacs.routemappingapp.view;

import com.stacs.routemappingapp.controller.RoutingResource;
import com.stacs.routemappingapp.model.route.Route;
import com.stacs.routemappingapp.model.stop.Stop;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Scanner;

/**
 * Command Line Interface.
 * This class is used to display the menu and take in user input.
 * This class is used for testing backend functionality.
 */
public class CommandLine implements Serializable {

    RoutingResource routingResource ;

    private Scanner scanner;

    private boolean appState = true;

    private String dayOfWeek = "";

    /**
     * Calling the Routing Resource.
     *
     * @param routingResource Routing Resource.
     */
    public CommandLine(RoutingResource routingResource) {
        this.routingResource = routingResource;
    }

    public void main(String[] args) {
        // Calling the run method.
        run();
    }

    public void run() {
        String line;
        scanner = new Scanner(System.in);
        instructions();
        try {
            do {
                line = scanner.nextLine();
                if (line.length() == 1) {
                    switch (line.charAt(0)) {
                        case '1' -> addDelRoute();
                        case '2' -> addDelStop();
                        case '3' -> viewMenu();
                        case '4' -> saveAppData();
                        case '5' -> loadAppData();
                        case '6' -> exit();
                        default -> System.out.println("Unknown Action...");
                    }
                } else if (line.equalsIgnoreCase("exit")) {
                    exit();
                    break;
                } else {
                    System.out.println("Error: Invalid Action \n");
                    run();
                }
            } while (line.charAt(0) >= '9' || line.length() != 1 || appState);
        } catch (StringIndexOutOfBoundsException | IOException e) {
            System.out.println("Empty Input received. Exiting Program...");
        }
    }


    /**
     * Add or Delete or View Routes.
     */
    private void addDelRoute() {
        final Scanner scanner = new Scanner(System.in);
        String line;
        System.out.println("Add or Delete Routes");
        String message = """
                1. Add a route \s
                2. Delete a route \s
                3. View Routes \s
                4. Main Menu \s
                """;
        System.out.println(message);
        line = scanner.nextLine();
        if (line.length() == 1) {
            switch (line.charAt(0)) {
                case '1' -> {
                    System.out.println("Adding a Route...");
                    System.out.println("Enter the Route Unique-ID: ");
                    line = scanner.nextLine().trim();
                    final String uniqueRouteNumber = line.trim();
                    System.out.println("Enter the Route Name: ");
                    line = scanner.nextLine().trim();
                    final String routeName = line.trim();
                    System.out.println("Enter the Destination :");
                    line = scanner.nextLine();
                    final String destination = line.trim();
                    System.out.println("Enter the Starting Point :");
                    line = scanner.nextLine();
                    final String startingPoint = line.trim();
                    try {
                        routingResource.checkIfIDValid(uniqueRouteNumber);
                        routingResource.checkAlphaNumeric(uniqueRouteNumber);
                        routingResource.checkIfNameValid(routeName);
                        routingResource.checkIfNameValid(destination);
                        routingResource.checkIfNameValid(startingPoint);
                        routingResource.checkNamingConvention(routeName);
                        routingResource.checkNamingConvention(destination);
                        routingResource.checkNamingConvention(startingPoint);
                        routingResource.addRoute(uniqueRouteNumber, routeName, destination, startingPoint);
                        System.out.println("Route Added Successfully!");
                        run();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        addDelRoute();
                    }
                }

                case '2' -> {
                    System.out.println("Deleting a Route...");
                    System.out.println("Enter the Route Unique-ID: ");
                    line = scanner.nextLine();
                    final String uniqueRouteNumber = line.trim();
                    try {
                        routingResource.checkIfIDValid(uniqueRouteNumber);
                        routingResource.checkAlphaNumeric(uniqueRouteNumber);
                        System.out.println(("Do you want to delete the Route? (Y/N)"));
                        line = scanner.nextLine().trim();
                        if (line.equalsIgnoreCase("Y")) {
                            routingResource.deleteRoute(uniqueRouteNumber);
                            // Should I delete the stops associated with the route?
                            // routingResource.deleteStop(uniqueRouteNumber);
                            System.out.println("The Route has been deleted successfully!");
                            run();
                        } else if (line.equalsIgnoreCase("N")) {
                            System.out.println("The Route has not been deleted!");
                            run();
                        } else {
                            System.out.println("Invalid Input!");
                            addDelRoute();
                        }

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        addDelRoute();
                    }
                }
                case '3' -> {
                    Map<String, Route> routeMap;
                    System.out.println("Viewing Routes...");
                    try {
                        routeMap = routingResource.callAllRoutes();
                        for (Map.Entry<String, Route> entry : routeMap.entrySet()) {
                            System.out.println("Route Identifier :" + entry.getKey());
                            System.out.println("Route Name :" + entry.getValue().getRouteName());
                            System.out.println("Destination :" + entry.getValue().getDestination());
                            System.out.println("Starting Point :" + entry.getValue().getStartingPoint());
                            System.out.println("--------------------------------------------");
                        }
                        System.out.println("All Routes have been displayed successfully!");
                        System.out.println("********************************************************");
                        run();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        addDelRoute();
                    }
                }
                case '4' -> {
                    System.out.println("Going back to Main Menu...");
                    run();
                }
            }
        }
    }

    /**
     * Add or Delete or View Stops.
     */
    private void addDelStop() {
        final Scanner scanner = new Scanner(System.in);
        String line;
        System.out.println("Add or Delete Stops");
        String message = """
                1.Add a stop and schedule to a Route.
                2.Delete a stop schedule.
                3.View All Stops.
                4.Main Menu.
                 """;
        System.out.println(message);
        line = scanner.nextLine();
        if (line.length() == 1) {
            switch (line.charAt(0)) {
                case '1' -> {
                    System.out.println("Adding a Stop to a Route...");
                    System.out.println("Enter the Route Unique-ID: ");
                    line = scanner.nextLine();
                    final String uniqueRouteNumber = line.trim();
                    System.out.println("Enter the Route Name: ");
                    line = scanner.nextLine();
                    final String routeName = line.trim();
                    try {
                        routingResource.checkIfNameValid(routeName);
                        routingResource.checkNamingConvention(routeName);
                        routingResource.checkIfIDValid(uniqueRouteNumber);
                        routingResource.checkAlphaNumeric(uniqueRouteNumber);
                        routingResource.checkIfRouteNameIDMatch(uniqueRouteNumber, routeName);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        addDelStop();
                    }
                    System.out.println("Enter a Schedule Identifier: ");
                    line = scanner.nextLine();
                    final String scheduleIdentifier = line.trim();
                    try {
                        routingResource.checkIfScheduleIdentifierValid(scheduleIdentifier);
                        routingResource.checkAlphaNumeric(scheduleIdentifier);
                        routingResource.checkIfScheduleIdentifierExists(scheduleIdentifier);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        addDelStop();
                    }
                    System.out.println("Enter the Stop Name: ");
                    line = scanner.nextLine();
                    final String stopName = line.trim();
                    try {
                        routingResource.checkIfStopNameValid(stopName);
                        routingResource.checkAlphaNumeric(stopName);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        addDelStop();
                    }
                    System.out.println("Enter the Stop's Location: ");
                    line = scanner.nextLine();
                    final String stopLocation = line.trim();
                    try {
                        routingResource.checkStopLocNamingConvention(stopLocation);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        addDelStop();
                    }
                    System.out.println("Enter the Date of the Schedule: (dd-mm-yyyy) ");
                    line = scanner.nextLine();
                    final String date = line.trim();
                    try {
                        routingResource.checkIfDateValid(date);
                        dayOfWeek = routingResource.getDayOfWeek(date).trim();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        addDelStop();
                    }
                    System.out.println("The day of the week is: " + dayOfWeek + "\n");
                    System.out.println("Enter the Departure Time: (hh:mm) ");
                    line = scanner.nextLine();
                    final String departureTime = line.trim();
                    try {
                        routingResource.checkIfTimeValid(departureTime);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        addDelStop();
                    }
                    System.out.println("Enter the Arrival Time: (hh:mm) ");
                    line = scanner.nextLine();
                    final String arrivalTime = line.trim();
                    try {
                        routingResource.checkIfTimeValid(arrivalTime);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        addDelStop();
                    }

                    try {
                        routingResource.isBeforeTime(departureTime, arrivalTime);
                        routingResource.addStop(uniqueRouteNumber, routeName, scheduleIdentifier, stopName, stopLocation, date, dayOfWeek, departureTime, arrivalTime);
                        System.out.println("Stop Added Successfully!");
                        run();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        addDelStop();
                    }
                }
                case '2' -> {
                    System.out.println("Deleting a Stop Schedule...");
                    System.out.println("Enter the Schedule Identifier: ");
                    line = scanner.nextLine();
                    final String scheduleIdentifier = line.trim();
                    System.out.println("Are you sure you want to delete this Schedule? (Y/N) ");
                    line = scanner.nextLine();
                    final String answer = line.trim();
                    if (answer.equalsIgnoreCase("Y")) {
                        try {
                            routingResource.deleteStop(scheduleIdentifier);
                            System.out.println("Stop Schedule Deleted Successfully!");
                            run();
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                            addDelStop();

                        }
                    } else if (answer.equalsIgnoreCase("N")) {
                        System.out.println("Going back to Main Menu...");
                        run();
                    } else {
                        System.out.println("Unknown Action...");
                        addDelStop();
                    }
                }
                case '3' -> {
                    Map<String, Stop> viewStops;
                    System.out.println("Viewing All Stops... \n");
                    System.out.println("--------------------------------------------------");
                    try {
                        viewStops = routingResource.viewAllStops();
                        for (Map.Entry<String, Stop> entry : viewStops.entrySet()) {
                            System.out.println("Stop Schedule Identifier: " + entry.getKey());
                            System.out.println("Stop Name: " + entry.getValue().getStopName());
                            System.out.println("Stop Location: " + entry.getValue().getStopLocation());
                            System.out.println("Stop Route Unique-ID: " + entry.getValue().getUniqueRouteNumber());
                            System.out.println("Stop Route Name: " + entry.getValue().getRouteName());
                            System.out.println("Stop Date: " + entry.getValue().getDate());
                            System.out.println("Stop Day of the Week: " + entry.getValue().getDay());
                            System.out.println("Stop Departure Time: " + entry.getValue().getDepartureTime());
                            System.out.println("Stop Arrival Time: " + entry.getValue().getArrivalTime());
                            System.out.println("--------------------------------------------------");
                        }
                        System.out.println("All Stops Listed Successfully!");
                        run();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        addDelStop();
                    }
                }
                case '4' -> {
                    System.out.println("Going back to Main Menu...");
                    run();
                }
                default -> {
                    System.out.println("Unknown Action...");
                    addDelStop();
                }
            }

        }
    }

    private void viewMenu() {
        System.out.println("* *********************** *");
        System.out.println("View Menu By :");
        String message = """
                1. Listing routes on a given stop \s
                2. Listing all routes on a given stop at a certain time of day \s
                3. Listing all times through the day a stop has service \s
                4. Back  to Main Menu ! \s
                *  *********************** *
                """;
        System.out.println(message);
        final Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        if (line.length() == 1) {
            switch (line.charAt(0)) {
                case '1' -> {
                    Map<String, Stop> viewRoutesByStop;
                    Map<String, Route> viewRouteInfoByStopName;
                    String routeName;
                    String routeIdentifier;
                    System.out.println("Listing routes on a given stop");
                    System.out.println("Note: You can view all Stop by Main Menu -> Stop Menu ");
                    System.out.println("******************************************************");
                    //LOOP OVER THE HASHMAP AND PRINT THE ROUTES
                    System.out.println("Enter the Stop Name: ");
                    line = scanner.nextLine();
                    final String stopName = line.trim();
                    try {

                        routingResource.checkStopNamingConvention(stopName);
                        routingResource.checkIfStopExists(stopName);
                        viewRoutesByStop = routingResource.viewRoutesByStop(stopName);

                        for (Map.Entry<String, Stop> entry : viewRoutesByStop.entrySet()) {
                            routeName = entry.getValue().getRouteName();
                            routeIdentifier = entry.getValue().getUniqueRouteNumber();
                            System.out.println("Stop Schedule Identifier: " + entry.getKey());
                            System.out.println("Stop Name: " + entry.getValue().getStopName());
                            System.out.println("Stop Location: " + entry.getValue().getStopLocation());
                            System.out.println("Stop Route Unique-ID: " + routeIdentifier);
                            System.out.println("Stop Route Name: " + routeName);
                            try {
                                viewRouteInfoByStopName = routingResource.getRouteInfoByStopName(routeIdentifier);
                                for (Map.Entry<String, Route> entry1 : viewRouteInfoByStopName.entrySet()) {
                                    System.out.println("Leaving from: " + entry1.getValue().getDestination());
                                    System.out.println("Arriving at: " + entry1.getValue().getStartingPoint());
                                }
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                                viewMenu();
                            }
                            System.out.println("Stop Date: " + entry.getValue().getDate());
                            System.out.println("Stop Day of the Week: " + entry.getValue().getDay());
                            System.out.println("Stop Departure Time: " + entry.getValue().getDepartureTime());
                            System.out.println("Stop Arrival Time: " + entry.getValue().getArrivalTime());
                            System.out.println("--------------------------------------------------");

                        }
                        System.out.println("Routes Listed Successfully!");
                        run();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        viewMenu();
                    }
                }
                // Rewrite the entire case 2
                case '2' -> {
                    Map<String, Stop> viewRoutesByStopTime;
                    Map<String, Route> viewRouteInfoByStopName;
                    System.out.println("Listing all routes on a given stop at a certain time of day");
                    System.out.println("Note: You can view all Stop by Main Menu -> Stop Menu ");
                    System.out.println("******************************************************");
                    System.out.println("Enter the Stop Name: ");
                    line = scanner.nextLine();
                    final String stopName = line.trim();
                    try {
                        routingResource.checkStopNamingConvention(stopName);
                        routingResource.checkIfStopExists(stopName);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        viewMenu();
                    }
                    System.out.println("Enter the time: (format: HH:MM)");
                    line = scanner.nextLine();
                    final String time = line.trim();
                    try {
                        routingResource.checkIfTimeValid(time);
                        viewRoutesByStopTime = routingResource.ifStopExistsbyTime(stopName, time);
                        for (Map.Entry<String, Stop> entry : viewRoutesByStopTime.entrySet()) {
                            String routeName = entry.getValue().getRouteName();
                            String routeIdentifier = entry.getValue().getUniqueRouteNumber();
                            System.out.println("Stop Schedule Identifier: " + entry.getKey());
                            System.out.println("Stop Name: " + entry.getValue().getStopName());
                            System.out.println("Stop Location: " + entry.getValue().getStopLocation());
                            System.out.println("Stop Route Unique-ID: " + routeIdentifier);
                            System.out.println("Stop Route Name: " + routeName);
                            try {
                                viewRouteInfoByStopName = routingResource.getRouteInfoByStopName(routeIdentifier);
                                for (Map.Entry<String, Route> entry1 : viewRouteInfoByStopName.entrySet()) {
                                    System.out.println("Leaving from: " + entry1.getValue().getDestination());
                                    System.out.println("Arriving at: " + entry1.getValue().getStartingPoint());
                                }
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                                viewMenu();
                            }
                            System.out.println("Stop Date: " + entry.getValue().getDate());
                            System.out.println("Stop Day of the Week: " + entry.getValue().getDay());
                            System.out.println("Stop Departure Time: " + entry.getValue().getDepartureTime());
                            System.out.println("Stop Arrival Time: " + entry.getValue().getArrivalTime());
                            System.out.println("--------------------------------------------------");
                            System.out.println("Routes Listed Successfully!");
                            run();
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        viewMenu();
                    }
                }

                case '3' -> {
                    Map<String, Stop> viewRoutesByStopDay;
                    Map<String, Route> viewRouteInfoByStopName;
                    System.out.println("Listing all times through the day a stop has service.");
                    System.out.println("Note: You can view all Stop by Main Menu -> Stop Menu ");
                    System.out.println("******************************************************");
                    System.out.println("Enter the Stop Name: ");
                    line = scanner.nextLine();
                    final String stopName = line.trim();
                    System.out.println("Enter the day of the week: ");
                    line = scanner.nextLine().trim();
                    final String day = line.trim();
                    try {
                        // Call the controller method to get the list of stops
                        viewRoutesByStopDay = routingResource.viewRoutesByStopDay(stopName, day);
                        for (Map.Entry<String, Stop> entry : viewRoutesByStopDay.entrySet()) {
                            String routeName = entry.getValue().getRouteName();
                            String routeIdentifier = entry.getValue().getUniqueRouteNumber();
                            System.out.println("Stop Schedule Identifier: " + entry.getKey());
                            System.out.println("Stop Name: " + entry.getValue().getStopName());
                            System.out.println("Stop Location: " + entry.getValue().getStopLocation());
                            System.out.println("Stop Route Unique-ID: " + routeIdentifier);
                            System.out.println("Stop Route Name: " + routeName);
                            try {
                                viewRouteInfoByStopName = routingResource.getRouteInfoByStopName(routeIdentifier);
                                for (Map.Entry<String, Route> entry1 : viewRouteInfoByStopName.entrySet()) {
                                    System.out.println("Leaving from: " + entry1.getValue().getDestination());
                                    System.out.println("Arriving at: " + entry1.getValue().getStartingPoint());
                                }
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                                viewMenu();
                            }
                            System.out.println("Stop Date: " + entry.getValue().getDate());
                            System.out.println("Stop Day of the Week: " + entry.getValue().getDay());
                            System.out.println("Stop Departure Time: " + entry.getValue().getDepartureTime());
                            System.out.println("Stop Arrival Time: " + entry.getValue().getArrivalTime());
                            System.out.println("--------------------------------------------------");
                            System.out.println("Routes Listed Successfully!");
                            run();
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        viewMenu();
                    }
                }
                case '4' -> run();
                default -> {
                    System.out.println("Unknown Action...");
                    viewMenu();
                }
            }
        } else {
            System.out.println("Error: Invalid Action \n");
            run();
        }
    }


    public void instructions() {
        System.out.println("************************************");
        System.out.println("Enter the option number to continue:");
        System.out.println("1. Route Menu");
        System.out.println("2. Stop Menu");
        System.out.println("3. View Menu");
        System.out.println("4. Save App Data");
        System.out.println("5. Load App Data");
        System.out.println("6. Exit");
        System.out.println("*************************************************");
        System.out.println("Enter your choice: ");
    }


    private void saveAppData() throws IOException {
        System.out.println("Saving App Data...");
        routingResource.saveAppData();
        System.out.println("App Data Saved Successfully!");
        run();
    }

    private void loadAppData() throws IOException {
        System.out.println("Loading App Data...");
        routingResource.loadAppData();
        System.out.println("App Data Loaded Successfully!");
        run();

    }


    public void exit() {
        System.out.println("************************************");
        System.out.println("Exiting the Application.....");
        System.out.println("************************************");
        setAppState(false);
    }

    private void setAppState(boolean appState) {
        this.appState = appState;
    }
}
