package com.stacs.routemappingapp;

import com.stacs.routemappingapp.service.RouteService;
import com.stacs.routemappingapp.view.CommandLine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
