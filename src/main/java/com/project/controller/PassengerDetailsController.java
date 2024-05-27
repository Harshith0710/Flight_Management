package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PassengerDetailsController {

    @GetMapping("/passenger-details")
    public String showPassengerDetailsForm(@RequestParam("flightId") Long flightId) {
        return "passenger-details-form"; 
    }
}
