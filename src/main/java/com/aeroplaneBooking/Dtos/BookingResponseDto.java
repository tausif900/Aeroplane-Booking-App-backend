package com.aeroplaneBooking.Dtos;

import java.math.BigDecimal;
import java.util.List;

import com.aeroplaneBooking.Enum.BookingStatus;
import com.aeroplaneBooking.Enum.PaymentStatus;
import com.aeroplaneBooking.entities.PassengerEntity;
import com.aeroplaneBooking.entities.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDto {

	private Integer bookingId;

	private Integer userId;

	private List<PassengerEntity> passenger;

//	flight offer id
	private String flightId;

	private String email;

	private String phoneNumber;

	private BigDecimal totalAmount;

	private String currency;

	private BigDecimal baseAmount;

	private BigDecimal taxAmount;

	private BookingStatus bookingStatus;

	private PaymentStatus paymentStatus;
}
