package com.stacs.routemappingapp.service;

import com.stacs.routemappingapp.model.stop.Stop;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service for Stop.
 * This class is responsible for all the logic behind the Stop.
 */
@Service
public class StopService implements Serializable {

    private static Map<String, Stop> stop = new HashMap<>();

    /**
     * This method is used to save the data to a file.
     *
     * @throws IOException if the file is not found.
     */
    public static void saveAppData() throws IOException {
        FileOutputStream f = new FileOutputStream("src/main/resources/data/stop.ser");
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(stop);
        o.close();
        f.close();
    }

    /**
     * This method is used to load the data from a file.
     *
     * @throws IOException if the file is not found.
     */
    public static void loadAppData() {
        try {
            FileInputStream fi = new FileInputStream("src/main/resources/data/stop.ser");
            ObjectInputStream oi = new ObjectInputStream(fi);
            //Read objects
            stop = (Map<String, Stop>) oi.readObject();
            oi.close();
            fi.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is used to check if the Stop ID is valid.
     *
     * @param stopNumber Stop ID.
     */
    public void checkIfStopIDValid(String stopNumber) {
        if (stopNumber.isEmpty()) {
            throw new IllegalArgumentException("No Stop ID is given. \n");
        }
    }

    /**
     * This method is used for getting the day of the week.
     *
     * @param date Date in the format dd-mm-yyyy.
     * @return The day of the week.
     */
    public String getDayOfWeek(String date) {
        String dayString;
        String[] dateArray = date.split("-");
        int day = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int year = Integer.parseInt(dateArray[2]);
        LocalDate localDate = LocalDate.of(year, month, day);
        java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        dayString = dayOfWeek.toString();
        return dayString;
    }

    /**
     * This method is used to check if the date is valid.
     *
     * @param uniqueRouteNumber  Route ID.
     * @param routeName          Route Name.
     * @param scheduleIdentifier Schedule Identifier.
     * @param stopName           Stop Name.
     * @param stopLocation       Stop Location.
     * @param date               Date in the format dd-mm-yyyy.
     * @param day                Day of the week.
     * @param departureTime      Departure Time in the format hh:mm.
     * @param arrivalTime        Arrival Time in the format hh:mm.
     */

    public void addStop(String uniqueRouteNumber, String routeName, String scheduleIdentifier, String stopName, String stopLocation, String date, String day, String departureTime, String arrivalTime) {
        if (stop.containsKey(scheduleIdentifier.toLowerCase())) {
            throw new IllegalArgumentException("Schedule Identifier already exists. \n");
        } else {
            stop.put(scheduleIdentifier.toLowerCase(), new Stop(uniqueRouteNumber.toLowerCase(), routeName.toLowerCase(), scheduleIdentifier.toLowerCase(), stopName.toLowerCase(), stopLocation.toLowerCase(), date.toLowerCase(), day.toLowerCase(), departureTime.toLowerCase(), arrivalTime.toLowerCase()));
        }
    }

    /**
     * This method is used to check if the Stop ID is valid.
     *
     * @param stopName Stop Name.
     * @return A list of all the stops.
     */
    public HashMap<String, Stop> viewRoutesByStop(String stopName) {
        HashMap<String, Stop> viewRoutesByStop = new HashMap<>();
        if (stopName.isEmpty() || stopName.isBlank()) {
            throw new IllegalArgumentException("No Stop Name is given. \n");
        } else if (!stopName.isEmpty() || !stopName.isBlank()) {
            for (Map.Entry<String, Stop> entry : stop.entrySet()) {
                if (entry.getValue().getStopName().equalsIgnoreCase(stopName)) {
                    viewRoutesByStop.put(entry.getKey(), entry.getValue());
                }
            }
        } else {
            throw new IllegalArgumentException("Stop Name does not exist. \n");
        }

        return viewRoutesByStop;
    }

    /**
     * This method is used to check if the date is valid.
     *
     * @param date Date in the format dd-mm-yyyy.
     */
    public void checkIfDateValid(String date) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-uuuu");
        {
            try {
                LocalDate.parse(date, f);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Error" + e);
            }
        }
    }

    /**
     * This method is used to check if the time is valid.
     *
     * @param time Time in the format hh:mm.
     */
    public void checkIfTimeValid(String time) {
        if (time.isEmpty() || time.isBlank()) {
            throw new IllegalArgumentException("No Time is given. \n");
        } else if (!time.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
            throw new IllegalArgumentException("Invalid Time Format");
        }
    }

    /**
     * This method is used to check if the Stop Name is valid and follows the naming convention.
     *
     * @param name Name of the Stop.
     */
    public void checkStopNamingConvention(String name) {
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("No Name is given. \n");
        } else if (name.matches("[a-zA-Z0-9]+")) {

        } else {
            throw new IllegalArgumentException("Invalid Name Format");
        }
    }

    // Next Few Methods...

    /**
     * This method checks if the Schedule Identifier is not empty.
     *
     * @param scheduleIdentifier Schedule Identifier.
     */
    public void checkIfScheduleIdentifierValid(String scheduleIdentifier) {
        if (scheduleIdentifier.isEmpty()) {
            throw new IllegalArgumentException("No Schedule Identifier is given. \n");
        }
    }

    /**
     * This method is used to check if arrival time is after departure time.
     *
     * @param departureTime Departure Time in the format hh:mm.
     * @param arrivalTime   Arrival Time in the format hh:mm.
     */
    public void isBeforeTime(String departureTime, String arrivalTime) {
        if (departureTime.compareTo(arrivalTime) > 0) {
            throw new IllegalArgumentException("Departure Time is after Arrival Time. \n");
        }
    }

    /**
     * This method is used to check if the Stop Name is valid.
     *
     * @param stopName Stop Name.
     */
    public void checkIfStopNameValid(String stopName) {
        if (stopName.isEmpty()) {
            throw new IllegalArgumentException("No Stop Name is given. \n");
        }
    }

    /**
     * This method is used to check if the Stop Location is valid and follows the naming convention.
     *
     * @param stopLocation Stop Location.
     */
    public void checkStopLocNamingConvention(String stopLocation) {
        if (stopLocation.isEmpty() || stopLocation.isBlank()) {
            throw new IllegalArgumentException("No Stop Location is given. \n");
        } else if (stopLocation.matches("[a-zA-Z0-9]+")) {

        } else {
            throw new IllegalArgumentException("Invalid Stop Location Format \n");
        }
    }

    /**
     * This method is used to check if the Schedule Identifier exists in the system.
     *
     * @param scheduleIdentifier Schedule Identifier.
     */
    public void checkIfScheduleIdentifierExists(String scheduleIdentifier) {
        if (stop.containsKey(scheduleIdentifier.toLowerCase())) {
            throw new IllegalArgumentException(" Schedule Identifier exists in the system, Create a new Identifier. \n");
        }
    }

    /**
     * This method is used for deleting a Stop.
     *
     * @param scheduleIdentifier Schedule Identifier.
     */
    public void deleteStop(String scheduleIdentifier) {
        if (stop.containsKey(scheduleIdentifier.toLowerCase())) {
            stop.remove(scheduleIdentifier.toLowerCase());
        } else {
            throw new IllegalArgumentException("No Stop exists in the system. \n");
        }
    }

    /**
     * This method is for viewing all the stops.
     *
     * @return A list of all the stops.
     */
    public HashMap<String, Stop> viewAllStops() {
        HashMap<String, Stop> viewAllStops = new HashMap<>();
        if (stop.isEmpty()) {
            throw new IllegalArgumentException("No stops exists in the system. \n");
        } else {
            for (Map.Entry<String, Stop> entry : stop.entrySet()) {
                viewAllStops.put(entry.getKey(), entry.getValue());
            }
        }
        return viewAllStops;

    }


    /**
     * This method is used to view all the stops by day.
     *
     * @param stopName Stop Name.
     * @param day      Day.
     */

    public Map<String, Stop> viewRoutesByStopDay(String stopName, String day) {
        Map<String, Stop> viewRoutesByStopDay = new HashMap<>();
        if (stopName.isEmpty() || stopName.isBlank() || day.isEmpty() || day.isBlank()) {
            throw new IllegalArgumentException("No Stop Name or Day is given. \n");
        } else {
            for (Map.Entry<String, Stop> entry : stop.entrySet()) {
                if (stopName.equalsIgnoreCase(entry.getValue().getStopName()) && day.equalsIgnoreCase(entry.getValue().getDay())) {
                    viewRoutesByStopDay.put(entry.getKey(), entry.getValue());
                }
            }
            if (viewRoutesByStopDay.isEmpty()) {
                throw new IllegalArgumentException("No Stop Name or Day is found. \n");
            }
        }
        return viewRoutesByStopDay;
    }

    /**
     * This method is used to check if the Stop Name exists in the system.
     *
     * @param stopName Stop Name.
     */
    public void checkIfStopExists(String stopName) {
        int count = 0;
        if (stopName.isEmpty() || stopName.isBlank()) {
            throw new IllegalArgumentException("No Stop Name is given. \n");
        } else if (!stopName.isEmpty() || !stopName.isBlank()) {
            for (Map.Entry<String, Stop> entry : stop.entrySet()) {
                if (entry.getValue().getStopName().equalsIgnoreCase(stopName)) {
                    count += 1;
                    break;
                }
            }
            if (count == 0) {
                throw new IllegalArgumentException("No Stop exists in the system. \n");
            }
        }
    }

    /**
     * This method wipes all the stops in the system.
     * Used for testing purposes.
     */
    public void wipeStops() {
        stop.clear();
    }

    /**
     * This method is used to check if the Stop Name exists in the system at the given time.
     *
     * @param stopName Stop Name.
     * @param time     Time in the format hh:mm.
     * @return A list of all the stops.
     */

    public Map<String, Stop> ifStopExistsbyTime(String stopName, String time) {
        Map<String, Stop> ifStopExistsbyTime = new HashMap<>();
        if (stopName.isEmpty() || stopName.isBlank() || time.isEmpty() || time.isBlank()) {
            throw new IllegalArgumentException("No Stop Name or Time is given. \n");
        } else {
            for (Map.Entry<String, Stop> entry : stop.entrySet()) {
                if (entry.getValue().getStopName().equalsIgnoreCase(stopName)) {
                    String arrivalTime = entry.getValue().getArrivalTime();
                    String departureTime = entry.getValue().getDepartureTime();
                    if (arrivalTime.compareTo(time) < 0 || departureTime.compareTo(time) > 0) {
                        throw new IllegalArgumentException("No Stop exists in the system at the given time. \n");
                    } else {
                        ifStopExistsbyTime.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
        return ifStopExistsbyTime;
    }

    /**
     * This method is used to check if the Stop Name exists in the system at the given time.
     * @return A list of all the stops.
     * For the front end development use.
     */
    //Add by Li
    public List<Stop> getStops() {
        return new ArrayList<>(stop.values());
    }
}
