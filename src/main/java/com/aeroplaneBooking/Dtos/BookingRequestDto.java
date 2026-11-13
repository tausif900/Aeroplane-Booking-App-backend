package com.aeroplaneBooking.Dtos;

import java.util.List;

import com.aeroplaneBooking.entities.UserEntity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDto {

	private Integer bookingId;

	@Valid
	private List<PassengerDto> passenger;

//	flight offer id
	@NotNull
	@NotBlank(message = "Flight Id cann't be blank")
	private String flightId;

	@NotNull
	@NotBlank(message = "email cann't be blank")
	private String email;

	@NotNull
	@NotBlank(message = "phone number cann't be blank")
	private String phoneNumber;

}
