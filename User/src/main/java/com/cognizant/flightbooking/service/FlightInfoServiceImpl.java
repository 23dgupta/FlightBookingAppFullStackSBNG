package com.cognizant.flightbooking.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.flightbooking.entity.FlightInfo;
import com.cognizant.flightbooking.repo.FlightInfoRepo;

@Service
public class FlightInfoServiceImpl implements IFlightInfoService{
	
	@Autowired
	private FlightInfoRepo  flightInfoRepo ;

	@Override
	public List<FlightInfo> findFlights(String from, String to, Date departureDate) {
		// TODO Auto-generated method stub
		return flightInfoRepo.findFlights(from, to, departureDate);
	}

}
