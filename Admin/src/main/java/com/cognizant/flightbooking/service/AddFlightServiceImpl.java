package com.cognizant.flightbooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.flightbooking.entity.AddFlightDetails;
import com.cognizant.flightbooking.exception.ResourceNotFoundException;
import com.cognizant.flightbooking.repossitory.AddFlightRepository;

@Service
public class AddFlightServiceImpl implements IAddFlightService {

	@Autowired
	AddFlightRepository flightRepository;

	@Override
	public Integer saveFlight(AddFlightDetails addFlight) {
		// TODO Auto-generated method stub
		return flightRepository.save(addFlight).getFlightNumber();
	}

	@Override
	public List<AddFlightDetails> getAllFlight() {
		// TODO Auto-generated method stub
		return flightRepository.findAll();
	}

	@Override
	public AddFlightDetails getFlight(String flightName) {
		// TODO Auto-generated method stub
		return  flightRepository.findByOperatingAirlines(flightName)
				.orElseThrow(() -> new ResourceNotFoundException("flightName not found"));

	}

	@Override
	public AddFlightDetails getflightNumber(Integer flightNumber) {
		// TODO Auto-generated method stub
		return flightRepository.findByFlightNumber(flightNumber).get();

	}

//	@Override
//	public void deleteFlightByNumber(Integer flightNumber) {
//		// TODO Auto-generated method stub
//		//deleteFlightById
//
//		flightRepository.deleteById(flightNumber);
//	}
	
	@Override
	public void deleteFlightById(Integer flightNumber) {
		// TODO Auto-generated method stub
		//deleteFlightById
		AddFlightDetails existingflightNumber = getflightNumber(flightNumber);

		flightRepository.delete(existingflightNumber);
	}

	@Override
	public Integer updateFlight(AddFlightDetails addFlight, Integer flightNumber) {
		 AddFlightDetails existingFlight = getflightNumber(flightNumber);
		 existingFlight.setId(addFlight.getId()!=null ?addFlight.getId() : existingFlight.getId());
		 existingFlight.setFlightNumber(addFlight.getFlightNumber()!=null ? addFlight.getFlightNumber() :existingFlight.getFlightNumber());
		 existingFlight.setOperatingAirlines(addFlight.getOperatingAirlines()!=null ? addFlight.getOperatingAirlines() :existingFlight.getOperatingAirlines());
		
		 existingFlight.setFromPlace(addFlight.getFromPlace()!=null ? addFlight.getFromPlace() :existingFlight.getFromPlace());
		 existingFlight.setToPlace(addFlight.getToPlace()!=null ? addFlight.getToPlace() :existingFlight.getToPlace());
		 existingFlight.setStartDate(addFlight.getStartDate()!=null ? addFlight.getStartDate() :existingFlight.getStartDate());
		 existingFlight.setEndDate(addFlight.getEndDate()!=null ? addFlight.getEndDate() :existingFlight.getEndDate());
		 existingFlight.setScheduleDays(addFlight.getScheduleDays()!=null ? addFlight.getScheduleDays() :existingFlight.getScheduleDays());
		 existingFlight.setBusinessSeats(addFlight.getBusinessSeats()!=null ? addFlight.getBusinessSeats() :existingFlight.getBusinessSeats());
		 existingFlight.setNonBusinessSeats(addFlight.getNonBusinessSeats()!=null ? addFlight.getNonBusinessSeats() :existingFlight.getNonBusinessSeats());
		 existingFlight.setCost(addFlight.getCost()!=null ? addFlight.getCost() :existingFlight.getCost());
		 existingFlight.setDateOfDeparture(addFlight.getDateOfDeparture()!=null ? addFlight.getDateOfDeparture() :existingFlight.getDateOfDeparture());
		flightRepository.save(existingFlight);

		return existingFlight.getFlightNumber();
	}

	@Override
	public List<Object> findByfromPlaceAndtoPlace( String fromPlace,String toPlace) {
		// TODO Auto-generated method stub
		return  flightRepository.findByFromPlaceAndToPlace(fromPlace, toPlace);
	}

	@Override
	public List<Object> findByFromAndTo(String fromPlace, String toPlace) {
				return flightRepository.findByFromAndTo(fromPlace, toPlace);
	}



}
