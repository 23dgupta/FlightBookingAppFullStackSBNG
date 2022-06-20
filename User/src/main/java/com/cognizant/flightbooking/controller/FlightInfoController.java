package com.cognizant.flightbooking.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.flightbooking.entity.FlightInfo;
import com.cognizant.flightbooking.service.IFlightInfoService;

@RestController
public class FlightInfoController {
	
	@Autowired
	private IFlightInfoService  iFlightInfoService ;
	
	//http://localhost:9090/findFlight?from=AUS&to=NYC&departureDate=02-05-2018
	
	@GetMapping("/findFlight")
	public List<FlightInfo> findFlights(@RequestParam("from") String from ,@RequestParam("to") String to,
			@RequestParam("departureDate")  @DateTimeFormat(pattern = "MM-dd-yyyy")  Date departureDate){
		
		
		return iFlightInfoService.findFlights(from, to, departureDate);
		
	}
	

}
