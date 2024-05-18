package com.project.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.model.Airport;
import com.project.model.Flight;
import com.project.repository.AirportRepository;
import com.project.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Override
    @Transactional
    public Flight addOrUpdateFlight(String flightNumber, String originAirportCode, String destinationAirportCode,
                                    LocalDateTime departureTime, LocalDateTime arrivalTime, String airline, double price) {

        Airport originAirport = airportRepository.findByCode(originAirportCode);
        Airport destinationAirport = airportRepository.findByCode(destinationAirportCode);

        if (originAirport == null || destinationAirport == null) {
            throw new IllegalArgumentException("Invalid origin or destination airport code");
        }

        Flight flight = flightRepository.findByFlightNumber(flightNumber);
        if (flight == null) {
            flight = new Flight();
            flight.setFlightNumber(flightNumber);
        }

        flight.setOriginAirport(originAirport);
        flight.setDestinationAirport(destinationAirport);
        flight.setDepartureTime(departureTime);
        flight.setArrivalTime(arrivalTime);
        flight.setAirline(airline);
        flight.setPrice(price);

        return flightRepository.save(flight);
    }

    @Override
    @Transactional
    public void deleteFlight(Long flightId) {
        flightRepository.deleteById(flightId);
    }

    @Override
    public List<Flight> searchFlights(String departureAirportCode, String destinationAirportCode,
                                      LocalDate departureDate, LocalDate returnDate,
                                      int page, String sortBy, String sortOrder) {
        Airport departureAirport = airportRepository.findByCode(departureAirportCode);
        Airport destinationAirport = airportRepository.findByCode(destinationAirportCode);

        if (departureAirport == null || destinationAirport == null) {
            throw new IllegalArgumentException("Invalid departure or destination airport code");
        }

        PageRequest pageRequest = PageRequest.of(page - 1, PAGE_SIZE, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        Page<Flight> flightPage = flightRepository.findByOriginAirportAndDestinationAirportAndDepartureTimeBetween(
                departureAirport, destinationAirport, 
                departureDate.atStartOfDay(), departureDate.atTime(23, 59, 59), 
                pageRequest);

        return flightPage.getContent();
    }

    @Override
    public int getTotalPages(String departureAirportCode, String destinationAirportCode,
                             LocalDate departureDate, LocalDate returnDate) {
        Airport departureAirport = airportRepository.findByCode(departureAirportCode);
        Airport destinationAirport = airportRepository.findByCode(destinationAirportCode);

        if (departureAirport == null || destinationAirport == null) {
            throw new IllegalArgumentException("Invalid departure or destination airport code");
        }

        long totalCount = flightRepository.countByOriginAirportAndDestinationAirportAndDepartureTimeBetween(
                departureAirport, destinationAirport, 
                departureDate.atStartOfDay(), departureDate.atTime(23, 59, 59));

        return (int) Math.ceil((double) totalCount / PAGE_SIZE);
    }

    // Other methods and constants...

    private static final int PAGE_SIZE = 10;
}