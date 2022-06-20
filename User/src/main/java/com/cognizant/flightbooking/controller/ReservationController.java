package com.cognizant.flightbooking.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.flightbooking.entity.FlightInfo;
import com.cognizant.flightbooking.repo.FlightInfoRepo;

@RestController
public class ReservationController {
	
	@Autowired
	private FlightInfoRepo flightInfoRepo;
	
	@GetMapping("/bookFlight/{flightNumber}")
	public FlightInfo bookFlight(@PathVariable("flightNumber") String flightNumber) {
		
		System.out.println(flightNumber);
		
		FlightInfo flightInfo = flightInfoRepo.findById(flightNumber);
		
		return flightInfo;
	}

}
