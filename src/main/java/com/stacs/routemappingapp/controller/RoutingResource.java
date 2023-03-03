package com.stacs.routemappingapp.controller;

import com.stacs.routemappingapp.service.RouteService;
import com.stacs.routemappingapp.service.StopService;

import java.io.Serializable;

/**
 * This class acts as a controller and is used to route the requests to the appropriate service.
 */
public class RoutingResource implements Serializable {
    private RouteService routeService = new RouteService();
    private StopService stopService = new StopService();

    public RoutingResource() {
        this.routeService = routeService;
    }




}
