package com.project.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Airport;
import com.project.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    
    Flight findByFlightNumber(String flightNumber);
    
    Page<Flight> findByOriginAirportAndDestinationAirportAndDepartureTimeBetween(
            Airport originAirport, Airport destinationAirport,
            LocalDateTime departureStartTime, LocalDateTime departureEndTime,
            Pageable pageable);

    long countByOriginAirportAndDestinationAirportAndDepartureTimeBetween(
            Airport originAirport, Airport destinationAirport,
            LocalDateTime departureStartTime, LocalDateTime departureEndTime);
}
