package com.stacs.routemappingapp;

import com.stacs.routemappingapp.view.CommandLine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RouteMappingAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(RouteMappingAppApplication.class, args);
       // SpringApplication.run(CommandLine.class, args);


    }

}
