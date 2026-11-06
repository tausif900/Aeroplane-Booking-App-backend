package com.aeroplaneBooking.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aeroplaneBooking.Dtos.FlightSearchRequestDto;
import com.aeroplaneBooking.Service.FlightServie;
import com.aeroplaneBooking.entities.UserEntity;

@RestController
@RequestMapping("/flight")
@CrossOrigin
public class FlightController {

	@Autowired
	private FlightServie flightService;

	@PostMapping("/search")
	public ResponseEntity<String> searchFlight(@AuthenticationPrincipal UserEntity user,
			@RequestBody FlightSearchRequestDto request) {
		return ResponseEntity.ok(flightService.searchFlights(user.getUserId(), request));
	}

	@GetMapping("/airports")
	public ResponseEntity<String> searchAirports(@AuthenticationPrincipal UserEntity user, @RequestParam String query) {
		return ResponseEntity.ok(flightService.searchAirports(user.getUserId(), query));
	}

	@GetMapping("/{flightId}")
	public ResponseEntity<String> getFlightById(@PathVariable String flightId) {
		return ResponseEntity.ok(flightService.getFlightById(flightId));
	}

}
