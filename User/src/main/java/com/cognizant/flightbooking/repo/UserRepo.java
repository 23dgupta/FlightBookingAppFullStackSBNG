package com.cognizant.flightbooking.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.flightbooking.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{

	Optional<User> findById(Integer id);
	
	
 
}
