package com.stacs.routemappingapp.api;

import com.stacs.routemappingapp.service.StopService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/data")
public class StopDataController {

    @GetMapping("/stop/load")
    public void loadStop(){
        StopService.loadAppData();
    }
    @GetMapping("/stop/save")
    public String saveStop(){
        try{
            StopService.saveAppData();
        }catch (Exception e){
            return "Failure!";
        }
       return "Successful!";
    }
}
