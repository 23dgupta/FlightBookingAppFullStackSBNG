package com.cognizant.flightbooking.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.flightbooking.entity.Passenger;

public interface PassengerRepo extends JpaRepository<Passenger, Long>{

}
