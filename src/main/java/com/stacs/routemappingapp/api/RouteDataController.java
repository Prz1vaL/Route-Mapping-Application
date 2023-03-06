package com.stacs.routemappingapp.api;

import com.stacs.routemappingapp.service.RouteService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/data")
public class RouteDataController {

    @GetMapping("/route/load")
    public void loadStop(){
        RouteService.loadAppData();
    }
    @GetMapping("/route/save")
    public String saveStop(){
        try{
            RouteService.saveAppData();
        }catch (Exception e){
            return "Failure!";
        }
        return "Successful!";
    }
}