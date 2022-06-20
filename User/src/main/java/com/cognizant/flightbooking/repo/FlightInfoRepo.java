package com.cognizant.flightbooking.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cognizant.flightbooking.entity.FlightInfo;

public interface FlightInfoRepo extends JpaRepository<FlightInfo, Integer>{
	
	@Query("from FlightInfo where departureCity=:departureCity and arrivalCity=:arrivalCity and dateOfDeparture=:dateOfDeparture")
	
	List<FlightInfo> findFlights(@Param("departureCity") String from , @Param("arrivalCity") String to, @Param("dateOfDeparture") Date departureDate);

	
	//@Query("from FlightInfo where flightNumber=:flightNumber")
	FlightInfo findById(String flightNumber);

}
