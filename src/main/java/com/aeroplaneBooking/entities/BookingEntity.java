package com.aeroplaneBooking.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.aeroplaneBooking.Enum.BookingStatus;
import com.aeroplaneBooking.Enum.PaymentStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingId;

	@ManyToOne
	private UserEntity user;

	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
	private List<PassengerEntity> passenger = new ArrayList<>();

//	flight offer id
	private String flightId;

	private String email;

	private String phoneNumber;
	
	private BigDecimal totalAmount;

	private String currency;

	@Enumerated(EnumType.STRING)
	private BookingStatus bookingStatus;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;

}
