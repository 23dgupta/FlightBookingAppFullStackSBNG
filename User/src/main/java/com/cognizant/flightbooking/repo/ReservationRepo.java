package com.cognizant.flightbooking.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.flightbooking.entity.Reservation;

public interface ReservationRepo extends JpaRepository<Reservation, Long>{

	Optional<Reservation> findById(Integer flightNumber);

}
