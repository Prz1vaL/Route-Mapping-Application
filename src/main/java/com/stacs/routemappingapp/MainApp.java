package com.stacs.routemappingapp;

import com.stacs.routemappingapp.controller.RoutingResource;
import com.stacs.routemappingapp.service.StopService;
import com.stacs.routemappingapp.view.CommandLine;

public class MainApp {

    public static void main(String[] args) {
        // Instances of Service Layer
        RoutingResource routingService = new RoutingResource();

        // Instance of Controller Layer
        RoutingResource routingResource = new RoutingResource();
        StopService stopService = new StopService();

        // Terminal View Method is called
        System.out.println("Welcome to the Route Mapping App!");
        System.out.println("************************************");
        CommandLine cmdLine = new CommandLine(routingResource);
        cmdLine.run();
    }
}