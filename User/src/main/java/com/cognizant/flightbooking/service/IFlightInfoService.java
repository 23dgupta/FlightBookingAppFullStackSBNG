package com.cognizant.flightbooking.service;

import java.util.Date;
import java.util.List;

import com.cognizant.flightbooking.entity.FlightInfo;

public interface IFlightInfoService {
	
	List<FlightInfo> findFlights( String from ,  String to, Date departureDate);

}
