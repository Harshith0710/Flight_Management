package com.project.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.project.model.Flight;

public interface FlightService {
    Flight addOrUpdateFlight(String flightNumber, String originAirportCode, String destinationAirportCode,
                             LocalDateTime departureTime, LocalDateTime arrivalTime, String airline, double price);
                             
    void deleteFlight(Long flightId);

	int getTotalPages(String departureAirport, String destinationAirport, LocalDate departureDate,
			LocalDate returnDate);

	List<Flight> searchFlights(String departureAirport, String destinationAirport, LocalDate departureDate,
			LocalDate returnDate, int page, String sortBy, String sortOrder);
}