package com.cognizant.flightbooking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.flightbooking.entity.Reservation;
import com.cognizant.flightbooking.repo.ReservationRepo;

public class ReservationServiceImpl implements IReservationService {
	
	@Autowired
	private ReservationRepo  reservationRepo ;

	@Override
	public Optional<Reservation> bookFlight(Integer flightNumber) {
		// TODO Auto-generated method stub
		return reservationRepo.findById(flightNumber);
	}

}
