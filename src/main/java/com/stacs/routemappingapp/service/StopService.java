package com.stacs.routemappingapp.service;

import com.stacs.routemappingapp.model.stop.Stop;

import java.io.*;
import java.time.LocalDate;
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


}
