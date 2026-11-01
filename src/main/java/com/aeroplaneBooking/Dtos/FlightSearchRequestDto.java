package com.aeroplaneBooking.Dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightSearchRequestDto {

	private String origin;

	private String destination;

	private LocalDate departureDate;

	private Integer adults;

	private String cabinClass;
}
