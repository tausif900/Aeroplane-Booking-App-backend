package com.aeroplaneBooking.Dtos;

import java.time.LocalDate;

import com.aeroplaneBooking.entities.BookingEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {

	private Integer passengerId;

	@NotNull
	@NotBlank(message = "first name cann't be blank")
	private String firstName;

	@NotNull
	@NotBlank(message = "last name cann't be blank")
	private String lastName;

	@NotNull
	@NotBlank(message = "Flight Id cann't be blank")
	private LocalDate dateOfBirth;

	@NotNull
	@NotBlank(message = "gender cann't be blank")
	private String gender;

	@NotNull
	@NotBlank(message = "passport number cann't be blank")
	private String passportNumber;

	@NotNull
	@NotBlank(message = "passport expiry date cann't be blank")
	private LocalDate passportExpiryDate;

	@NotNull
	@NotBlank(message = "nationality cann't be blank")
	private String nationality;

//	Booking Id
	private BookingEntity booking;

}
