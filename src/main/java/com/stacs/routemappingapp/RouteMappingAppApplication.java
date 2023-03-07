package com.stacs.routemappingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Spring Boot application.
 * This class is responsible for starting the application.
 */
@SpringBootApplication
public class RouteMappingAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(RouteMappingAppApplication.class, args);
        // SpringApplication.run(CommandLine.class, args);
    }

//    @Bean
//    public RouteService routeService() {
//        return new RouteService();
//    }

}
