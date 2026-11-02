package com.aeroplaneBooking.Service;

import com.aeroplaneBooking.Dtos.FlightSearchRequestDto;

public interface FlightServie {

	String searchFlights(Integer userId, FlightSearchRequestDto request);

	String searchAirports(Integer userId, String query);

}
