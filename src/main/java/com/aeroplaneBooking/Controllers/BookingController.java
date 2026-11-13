package com.aeroplaneBooking.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aeroplaneBooking.Dtos.BookingRequestDto;
import com.aeroplaneBooking.Dtos.BookingResponseDto;
import com.aeroplaneBooking.Service.BookingService;
import com.aeroplaneBooking.entities.UserEntity;

@RestController
@RequestMapping("/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping("/create-booking")
	public ResponseEntity<BookingResponseDto> createBooking(@AuthenticationPrincipal UserEntity user,
			@RequestBody BookingRequestDto request) {
		return new ResponseEntity<BookingResponseDto>(bookingService.createBooking(user.getUserId(), request),
				HttpStatus.CREATED);
	}
}
