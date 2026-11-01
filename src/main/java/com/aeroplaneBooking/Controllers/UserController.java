package com.aeroplaneBooking.Controllers;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aeroplaneBooking.Dtos.LoginRequestDto;
import com.aeroplaneBooking.Dtos.RegistrationRequestDto;
import com.aeroplaneBooking.Dtos.UserDto;
import com.aeroplaneBooking.SecurityConfig.JwtFilter.JwtService;
import com.aeroplaneBooking.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/user-registration")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody RegistrationRequestDto request) {
		UserDto registerUser = userService.registerUser(request);
		String token = jwtService.generateToken(registerUser.getEmail());
		ResponseCookie responseCookie = ResponseCookie.from("token", token).httpOnly(true).secure(false).path("/")
				.sameSite("Lax").maxAge(Duration.ofMillis(jwtService.getExpiration())).build();
		return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.SET_COOKIE, responseCookie.toString())
				.body(registerUser);
	}

	@PostMapping("/user-login")
	public ResponseEntity<UserDto> loginUser(@Valid @RequestBody LoginRequestDto request) {
		UserDto loginUser = userService.loginUser(request);
		String token = jwtService.generateToken(loginUser.getEmail());
		ResponseCookie responseCookie = ResponseCookie.from("token", token).httpOnly(true).secure(false).path("/")
				.sameSite("Lax").maxAge(Duration.ofMillis(jwtService.getExpiration())).build();
		return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.SET_COOKIE, responseCookie.toString())
				.body(loginUser);
	}

}
