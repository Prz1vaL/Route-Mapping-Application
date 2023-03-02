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



}
