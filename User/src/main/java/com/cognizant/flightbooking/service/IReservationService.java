package com.cognizant.flightbooking.service;

import java.util.Optional;

import com.cognizant.flightbooking.entity.Reservation;

public interface IReservationService {
	
	Optional<Reservation> bookFlight(Integer flightNumber);

}
