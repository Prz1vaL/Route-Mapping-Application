package com.stacs.routemappingapp;

import com.stacs.routemappingapp.controller.RoutingResource;
import com.stacs.routemappingapp.service.StopService;
import com.stacs.routemappingapp.view.CommandLine;

/**
 * This class is the main class of the application.
 * This class is used for testing backend functionality.
 */
public class MainApp {

    public static void main(String[] args) {
         // Instance of Controller Layer
        RoutingResource routingService = new RoutingResource();

        // Terminal View Method is called
        System.out.println("Welcome to the Route Mapping App!");
        System.out.println("************************************");
        CommandLine cmdLine = new CommandLine(routingResource);
        cmdLine.run();
    }
}
