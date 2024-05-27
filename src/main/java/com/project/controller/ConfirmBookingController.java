package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConfirmBookingController {

    @PostMapping("/confirm-booking")
    public String confirmBooking(@RequestParam("flightId") Long flightId,
                                 @RequestParam("name") String passengerName) {
        return "booking-confirmation"; 
    }
}
