package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    
	Airport findByCode (String code);
}