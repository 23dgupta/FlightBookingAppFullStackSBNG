package com.cognizant.flightbooking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cognizant.flightbooking.entity.FlightDetailsInfo;
import com.cognizant.flightbooking.entity.User;
import com.cognizant.flightbooking.repo.UserRepo;
import com.cognizant.flightbooking.service.UserServiceImpl;

import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user/api/v1.0")
@Api(tags= {"Flight User Booking RestApi"} ,description = " ")

//http://localhost:9090/swagger-ui.html
public class UserController {
	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/userRegistration")
	public Integer createUser(@RequestBody User user) {
		Integer id = userService.saveStudent(user);
		return id;
	}

//http://localhost:9090/user/api/v1.0/allFlights
	// done
	@GetMapping("/allFlights")
	public ResponseEntity<FlightDetailsInfo[]> getAllFlight() {

		ResponseEntity<FlightDetailsInfo[]> response = restTemplate.getForEntity("http://localhost:9094/allFlights",
				FlightDetailsInfo[].class);
		FlightDetailsInfo[] flightDetailsInfo = response.getBody();
		return response;

	}

	// http://localhost:9090/user/api/v1.0/searchflight/MUM/DEL
	// done
	@GetMapping("/searchflight/{fromPlace}/{toPlace}")
	public ResponseEntity<FlightDetailsInfo[]> searchFlight(@PathVariable("fromPlace") String fromPlace,
			@PathVariable("toPlace") String toPlace) {

		ResponseEntity<FlightDetailsInfo[]> response = restTemplate.getForEntity(
				"http://localhost:9094/search/{fromPlace}/{toPlace}", FlightDetailsInfo[].class, fromPlace, toPlace);
		Object[] flightDetailsInfo = response.getBody();
		return response;

	}
	
	
	// http://localhost:9090/user/api/v1.0/searchflights/MUM/DEL
		// TODO
	@GetMapping("/searchflights/{fromPlace}/{toPlace}")
	public Object[] specificFlightInfo(@PathVariable("fromPlace") String fromPlace,
			@PathVariable("toPlace") String toPlace) {

		ResponseEntity<FlightDetailsInfo[]> response = restTemplate.getForEntity(
				"http://localhost:9094/searchflight/{fromPlace}/{toPlace}", FlightDetailsInfo[].class, fromPlace, toPlace);
		Object[] flightDetailsInfo = response.getBody();
		return flightDetailsInfo;

	}

	@GetMapping("/getUserAll")
	public List<User> getAll() {

		return userService.getAllUser();

	}

	@GetMapping("/user/{id}")
	public Optional<User> getById(@PathVariable Integer id) {

		return userService.getUser(id);

	}

}
