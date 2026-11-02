package com.aeroplaneBooking.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aeroplaneBooking.Dtos.FlightSearchRequestDto;
import com.aeroplaneBooking.Service.FlightServie;

@RestController
@RequestMapping("/flight")
@CrossOrigin
public class FlightController {

	@Autowired
	private FlightServie flightService;

	@PostMapping("/search")
	public ResponseEntity<String> searchFlight(@RequestBody FlightSearchRequestDto request) {
		return ResponseEntity.ok(flightService.searchFlights(request));
	}

}
