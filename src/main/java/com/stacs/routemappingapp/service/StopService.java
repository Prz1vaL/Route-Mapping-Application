package com.stacs.routemappingapp.service;

import com.stacs.routemappingapp.model.stop.Stop;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class StopService implements Serializable {

    // First Few Methods...
    private static Map<String, Stop> stop = new HashMap<>();

    public static void saveAppData() throws IOException {
        FileOutputStream f = new FileOutputStream("src/main/resources/data/stop.ser");
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(stop);
        o.close();
        f.close();
    }

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

    public void checkIfStopIDValid(String stopNumber) {
        if (stopNumber.isEmpty()) {
            throw new IllegalArgumentException("No Stop ID is given. \n");
        }
    }

    public String getDayOfWeek(String date) {
        String dayString;
        String[] dateArray = date.split("/");
        int day = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int year = Integer.parseInt(dateArray[2]);
        LocalDate localDate = LocalDate.of(year, month, day);
        java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        dayString = dayOfWeek.toString();
        return dayString;
    }

    // Next Few Methods...
    public void addStop(String uniqueRouteNumber, String routeName, String scheduleIdentifier, String stopName, String stopLocation, String date, String day, String departureTime, String arrivalTime) {
        if (stop.containsKey(scheduleIdentifier.toLowerCase())) {
            throw new IllegalArgumentException("Schedule Identifier already exists. \n");
        } else {
            stop.put(scheduleIdentifier.toLowerCase(), new Stop(uniqueRouteNumber.toLowerCase(), routeName.toLowerCase(), scheduleIdentifier.toLowerCase(), stopName.toLowerCase(), stopLocation.toLowerCase(), date.toLowerCase(), day.toLowerCase(), departureTime.toLowerCase(), arrivalTime.toLowerCase()));
        }
    }

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

    public void checkIfDateValid(String date) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        {
            try {
                LocalDate.parse(date, f);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Error" + e);
            }
        }
    }

    public void checkIfTimeValid(String time) {
        if (time.isEmpty() || time.isBlank()) {
            throw new IllegalArgumentException("No Time is given. \n");
        } else if (!time.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
            throw new IllegalArgumentException("Invalid Time Format");
        }
    }

    public void checkStopNamingConvention(String name) {
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("No Name is given. \n");
        } else if (name.matches("[a-zA-Z0-9]+")) {

        } else {
            throw new IllegalArgumentException("Invalid Name Format");
        }
    }

    // Next Few Methods...

    public void checkIfScheduleIdentifierValid(String scheduleIdentifier) {
        if (scheduleIdentifier.isEmpty()) {
            throw new IllegalArgumentException("No Schedule Identifier is given. \n");
        }
    }

    public void isBeforeTime(String departureTime, String arrivalTime) {
        if (departureTime.compareTo(arrivalTime) > 0) {
            throw new IllegalArgumentException("Departure Time is after Arrival Time. \n");
        }
    }

    public void checkIfStopNameValid(String stopName) {
        if (stopName.isEmpty()) {
            throw new IllegalArgumentException("No Stop Name is given. \n");
        }
    }

    public void checkStopLocNamingConvention(String stopLocation) {
        if (stopLocation.isEmpty() || stopLocation.isBlank()) {
            throw new IllegalArgumentException("No Stop Location is given. \n");
        } else if (stopLocation.matches("[a-zA-Z0-9]+")) {

        } else {
            throw new IllegalArgumentException("Invalid Stop Location Format \n");
        }
    }

    public void checkIfScheduleIdentifierExists(String scheduleIdentifier) {
        if (stop.containsKey(scheduleIdentifier.toLowerCase())) {
            throw new IllegalArgumentException(" Schedule Identifier exists in the system, Create a new Identifier. \n");
        }
    }

    public void deleteStop(String scheduleIdentifier) {
        if (stop.containsKey(scheduleIdentifier.toLowerCase())) {
            stop.remove(scheduleIdentifier.toLowerCase());
        } else {
            throw new IllegalArgumentException("No Stop exists in the system. \n");
        }
    }

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

    public Map<String, Stop> viewRoutesByStopTime(String stopName, String arrivalTime) {
        Map<String, Stop> viewRoutesByStopTime = new HashMap<>();
        if (stopName.isEmpty() || stopName.isBlank()) {
            throw new IllegalArgumentException("No Stop Name is given. \n");
        } else {
            for (Map.Entry<String, Stop> entry : stop.entrySet()) {
                if (entry.getValue().getStopName().equalsIgnoreCase(stopName) && entry.getValue().getArrivalTime().equalsIgnoreCase(arrivalTime)) {
                    viewRoutesByStopTime.put(entry.getKey(), entry.getValue());
                }
            }
            if (viewRoutesByStopTime.isEmpty()) {
                throw new IllegalArgumentException("The given time does not match with any stops in the system. TRY AGAIN ! \n");
            }
        }

        return viewRoutesByStopTime;
    }

    public Map<String, Stop> viewRoutesByStopTimeDept(String stopName, String departureTime) {
        Map<String, Stop> viewRoutesByStopTimeDept = new HashMap<>();
        if (stopName.isEmpty() || stopName.isBlank()) {
            throw new IllegalArgumentException("No Stop Name is given. \n");
        } else if (!stopName.isEmpty() || !stopName.isBlank()) {
            for (Map.Entry<String, Stop> entry : stop.entrySet()) {
                if (entry.getValue().getStopName().equalsIgnoreCase(stopName) && entry.getValue().getDepartureTime().equalsIgnoreCase(departureTime)) {
                    viewRoutesByStopTimeDept.put(entry.getKey(), entry.getValue());
                }
            }
            if (viewRoutesByStopTimeDept.isEmpty()) {
                throw new IllegalArgumentException("The given time does not match with any stops in the system. TRY AGAIN ! \n");
            }
        }
        return viewRoutesByStopTimeDept;
    }

    // 3 More Methods... Remaining based on TDD.



}
