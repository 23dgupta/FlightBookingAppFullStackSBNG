package com.cognizant.flightbooking.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.flightbooking.entity.AddFlightDetails;
import com.cognizant.flightbooking.exception.ResourceNotFoundException;
import com.cognizant.flightbooking.service.IAddFlightService;

import io.swagger.annotations.Api;

@RestController
@CrossOrigin("*")
@Api(tags = { "Flight Booking RestApi" }, description = " ")
public class AddFlightController {

	private Logger logger = LoggerFactory.getLogger(AddFlightController.class);

	@Autowired
	private IAddFlightService flightService;

	/*
	 * 
	 * { "operatingAirlines":"SpiceJet", "departureCity":"MUM", "arrivalCity":"DEL",
	 * "arrivalTime":"1pm", "depature":"5am", yyyymmdd
	 * "dateOfDeparture":"2022-04-30", "flightCharge":3456 }
	 */
	// done
	@PostMapping("/addFlight")
	public ResponseEntity<String> addFlight(@RequestBody AddFlightDetails addFlight) throws IOException {

		ResponseEntity<String> resp = null;
		try {

			Integer flightNumber = flightService.saveFlight(addFlight);
			logger.info("flight data : {} " + addFlight);
			resp = new ResponseEntity<String>(
					new StringBuffer().append("Flight added with flightNumber ").append(flightNumber)
							// .append(" saved")
							.toString(),
					HttpStatus.CREATED);

		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to process addFlight", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;

	}

	// http://localhost:9094/allFlights
	// done

	@GetMapping("/allFlights")
	public ResponseEntity<List<AddFlightDetails>> getAllFlight() {
		ResponseEntity<List<AddFlightDetails>> resp = null;
		try {
			List<AddFlightDetails> list = flightService.getAllFlight();
			resp = new ResponseEntity<List<AddFlightDetails>>(list, HttpStatus.OK);

		} catch (Exception e) {
			resp = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

	// exception pending
	// http://localhost:9094/search/ZAB/DEL
	@GetMapping("/search/{fromPlace}/{toPlace}")
	public List<Object> findByfromPlaceAndtoPlace(@PathVariable("fromPlace") String fromPlace,
			@PathVariable("toPlace") String toPlace) {
		List<Object> fromPlaceAndtoPlace = null;

		try {
			if (fromPlace != null && toPlace != null) {
				fromPlaceAndtoPlace = flightService.findByfromPlaceAndtoPlace(fromPlace, toPlace);
				if (fromPlaceAndtoPlace.size() == 0) {
					throw new ResourceNotFoundException(
							"Unable to fetch resource from " + fromPlace + " toPlace " + toPlace);
				}
				System.out.println(fromPlaceAndtoPlace);

				return fromPlaceAndtoPlace;
			} else {
				throw new ResourceNotFoundException(
						"Unable to fetch resource from " + fromPlace + " toPlace " + toPlace);
			}

		} catch (Exception e) {
			e.printStackTrace();

			throw new ResourceNotFoundException("Unable to fetch resource from " + fromPlace + " toPlace " + toPlace);

		}

		// return fromPlaceAndtoPlace;

	}

	// working
	// http://localhost:9094/searchflight/MUM/GER
	// limited response
	//@GetMapping("/searchflight/{fromPlace}/{toPlace}")
	public List<Object> findByfromAndto(@PathVariable("fromPlace") String fromPlace,
			@PathVariable("toPlace") String toPlace) {
		List<Object> fromPlaceAndtoPlace = null;

		try {
			if (fromPlace != null && toPlace != null) {
				// int a=10/0;
				fromPlaceAndtoPlace = flightService.findByFromAndTo(fromPlace, toPlace);
				if (fromPlaceAndtoPlace.size() == 0) {
					throw new ResourceNotFoundException(
							"Unable to fetch resource from " + fromPlace + " toPlace " + toPlace);
				}
				System.out.println(fromPlaceAndtoPlace);

				return fromPlaceAndtoPlace;
			} else {
				throw new ResourceNotFoundException(
						"Unable to fetch resource from " + fromPlace + " toPlace " + toPlace);
			}

		} catch (Exception e) {
			e.printStackTrace();

			throw new ResourceNotFoundException("Unable to fetch resource from " + fromPlace + " toPlace " + toPlace);

		}

	}

	// http://localhost:9094/flightsByName/UK Airline
	// done
	@GetMapping("/flightsByName/{flightName}")

	public ResponseEntity<?> getFlightByName(@PathVariable("flightName") String flightName) {

		ResponseEntity<?> resp = null;

		try {
			AddFlightDetails name = flightService.getFlight(flightName);
			resp = new ResponseEntity<AddFlightDetails>(name, HttpStatus.OK);

		}

		catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to fetch flight with name " + flightName, HttpStatus.NOT_FOUND);
			e.printStackTrace();
		}
		return resp;

	}

	// http://localhost:9094/getFlightsByNumber?flightNumber=A233
	
	// done

	@GetMapping("/getFlightsByNumber/{flightNumber}")
	public ResponseEntity<?> getFlightByNumber(@PathVariable("flightNumber") Integer flightNumber) {
		ResponseEntity<?> resp = null;
		try {
			AddFlightDetails flightByNumber = flightService.getflightNumber(flightNumber);
			resp = new ResponseEntity<AddFlightDetails>(flightByNumber, HttpStatus.OK);

		}

		catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to fetch flight with Number " + flightNumber,
					HttpStatus.NOT_FOUND);
			e.printStackTrace();
		}
		return resp;

	}

	// http://localhost:9092/deleteFlight/A233
	// TODO

	@DeleteMapping("/deleteFlight/{flightNumber}")
	public ResponseEntity<?> deleteFlight(@PathVariable("flightNumber") Integer flightNumber) {

		ResponseEntity<?> resp = null;
		try {

			//flightService.deleteFlightById(flightNumber);
			//resp = ResponseEntity.ok("Flight Deleted with flightNumber " + flightNumber);
			flightService.deleteFlightById(flightNumber);
			Map<String,Boolean> response=new HashMap<>();
			response.put("Flight Deleted with flightNumber"+flightNumber, Boolean.TRUE);
			//resp = ResponseEntity.ok("Flight Deleted with flightNumber " + flightNumber);
			resp = ResponseEntity.ok(response);

		} catch (Exception e) {

			resp = new ResponseEntity<String>(" Flight not present with  this flightNumber  " + flightNumber,
					HttpStatus.NOT_FOUND);
			e.printStackTrace();
		}
		return resp;
	}

//	@DeleteMapping("/deleteFlight/{flightNumber}")
//	public ResponseEntity<?> deleteFlight(@PathVariable("flightNumber") Integer flightNumber) {
//
//		ResponseEntity<?> resp = null;
//		try {
//
//			flightService.deleteFlightByNumber(flightNumber);
//			resp = ResponseEntity.ok("Flight Deleted with id " + flightNumber);
//
//		} catch (Exception e) {
//
//			resp = new ResponseEntity<String>(" Flight not present with  this id  " + flightNumber,
//					HttpStatus.NOT_FOUND);
//			e.printStackTrace();
//		}
//		return resp;
//	}

	// http://localhost:9092/updateFlight/SA33
	// done
	@PutMapping("/updateFlight/{flightNumber}")
	public ResponseEntity<?> updateFlight(@PathVariable("flightNumber") Integer flightNumber,
			@RequestBody AddFlightDetails addFlight) {

		ResponseEntity<?> resp = null;

		// ResponseEntity<AddFlightDetails> flight = new
		// ResponseEntity<>(HttpStatus.OK);

		System.out.println(flightNumber);
		System.out.println(addFlight);
		try {
			Integer updateFlight = flightService.updateFlight(addFlight, flightNumber);
			//return ResponseEntity.ok(" Flight Updated with flightNumber " + flightNumber);
			resp= new ResponseEntity<AddFlightDetails>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp = new ResponseEntity<String>(" Flight record not exist with flightNumber " + flightNumber,
					HttpStatus.NOT_FOUND);
		}
		return resp;
	}

}
