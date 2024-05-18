package com.project.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.model.Flight;
import com.project.service.FlightService;

@Controller
public class SearchController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/search")
    public String searchFlights(Model model, @RequestParam("departureAirport") String departureAirport,
                                @RequestParam("destinationAirport") String destinationAirport,
                                @RequestParam("departureDate") LocalDate departureDate,
                                @RequestParam(value = "returnDate", required = false) LocalDate returnDate,
                                @RequestParam(value = "page", defaultValue = "1") int page,
                                @RequestParam(value = "sortBy", defaultValue = "departureTime") String sortBy,
                                @RequestParam(value = "sortOrder", defaultValue = "asc") String sortOrder) {

        // Query flights based on search criteria
        List<Flight> flights = flightService.searchFlights(departureAirport, destinationAirport,
                departureDate, returnDate, page, sortBy, sortOrder);

        // Calculate pagination information
        int totalPages = flightService.getTotalPages(departureAirport, destinationAirport,
                departureDate, returnDate);

        // Pass data to the template
        model.addAttribute("flights", flights);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortOrder", sortOrder);

        return "search-results";
    }
}
