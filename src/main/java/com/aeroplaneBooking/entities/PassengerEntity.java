package com.aeroplaneBooking.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer passengerId;
	
	private String firstName;
	
	private String lastName;
	
	private LocalDate dateOfBirth;
	
	private String gender;
	
	private String passportNumber;
	
	private LocalDate passportExpiryDate;
	
	private String nationality;
	
//	Booking Id
	@ManyToOne
	private BookingEntity booking;

}
