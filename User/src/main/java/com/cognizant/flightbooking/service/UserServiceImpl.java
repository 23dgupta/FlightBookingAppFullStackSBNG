package com.cognizant.flightbooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.flightbooking.entity.User;
import com.cognizant.flightbooking.repo.UserRepo;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	UserRepo userRepo;

	@Override
	public Integer saveStudent(User user) {
		User saveUser = userRepo.save(user);
		return saveUser.getId();
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return  userRepo.findAll();
	}

	@Override
	public Optional<User> getUser(Integer id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id);
	}

	

//	@Override
//	public User findByEmail(String email) {
//		// TODO Auto-generated method stub
//		return userRepo.findByEmail(email);
//	}

	

	
}
