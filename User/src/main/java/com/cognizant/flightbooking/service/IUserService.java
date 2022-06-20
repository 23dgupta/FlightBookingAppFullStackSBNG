package com.cognizant.flightbooking.service;

import java.util.List;
import java.util.Optional;

import com.cognizant.flightbooking.entity.User;

public interface IUserService {

	
	Integer saveStudent(User user);
	
	List<User> getAllUser();
	
	Optional<User> getUser(Integer id);
	
	//User findByEmail(String email);
	
}
