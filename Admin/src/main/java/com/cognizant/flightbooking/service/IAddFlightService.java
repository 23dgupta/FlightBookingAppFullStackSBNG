package com.cognizant.flightbooking.service;

import java.util.List;

import com.cognizant.flightbooking.entity.AddFlightDetails;

public interface IAddFlightService {

	Integer saveFlight(AddFlightDetails addFlight);

	public List<AddFlightDetails> getAllFlight();

	AddFlightDetails getFlight(String flightName);

	AddFlightDetails getflightNumber(Integer flightNumber);
	//AddFlightDetails searchFlightFromTo(String fromPlace,String toPlace);

	

	Integer updateFlight(AddFlightDetails addFlight, Integer flightNumber);

	//void deleteFlightByNumber(Integer flightNumber);  deleteFlightById
	
	void deleteFlightById(Integer flightNumber);
	
	List<Object> findByfromPlaceAndtoPlace( String fromPlace,String toPlace);
	
	List<Object> findByFromAndTo( String fromPlace,String toPlace);

}
