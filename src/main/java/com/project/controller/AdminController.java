package com.project.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.service.FlightService;

@Controller
public class AdminController {

    @Autowired
    private FlightService flightService;

    @PostMapping("/admin/add-flight")
    public String addFlight(@RequestParam("flightNumber") String flightNumber,
                            @RequestParam("originAirport") String originAirportCode,
                            @RequestParam("destinationAirport") String destinationAirportCode,
                            @RequestParam("departureTime") LocalDateTime departureTime,
                            @RequestParam("arrivalTime") LocalDateTime arrivalTime,
                            @RequestParam("airline") String airline,
                            @RequestParam("price") double price) {

        flightService.addOrUpdateFlight(flightNumber, originAirportCode, destinationAirportCode,
                                        departureTime, arrivalTime, airline, price);

        return "redirect:/admin-page";
    }
    @PostMapping("/admin/delete-flight")
    public String deleteFlight(@RequestParam Long flightId) {
      // Use the flightId to delete the flight
      flightService.deleteFlight(flightId);
      return "redirect:/admin-page";
    }



}
