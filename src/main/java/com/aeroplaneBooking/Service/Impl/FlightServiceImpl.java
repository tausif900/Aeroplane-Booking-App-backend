package com.aeroplaneBooking.Service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.aeroplaneBooking.Dtos.FlightSearchRequestDto;
import com.aeroplaneBooking.Service.FlightServie;

@Service
public class FlightServiceImpl implements FlightServie {

	@Autowired
	private RestClient restClient;

	@Override
	public String searchFlights(FlightSearchRequestDto request) {
		Map<String, Object> slice = new HashMap<>();
		slice.put("origin", request.getOrigin());
		slice.put("destination", request.getDestination());
		slice.put("departure_date", request.getDepartureDate().toString());

		List<Map<String, Object>> slices = new ArrayList<>();
		slices.add(slice);

		List<Map<String, Object>> passengers = new ArrayList<>();
		for (int i = 0; i < request.getAdults(); i++) {
			Map<String, Object> passenger = new HashMap<>();
			passenger.put("type", "adult");
			passengers.add(passenger);
		}

		Map<String, Object> data = new HashMap<>();
		data.put("slices", slices);
		data.put("passengers", passengers);
		data.put("cabin_class", request.getCabinClass());

		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("data", data);

		String response = restClient.post().uri("/air/offer_requests").contentType(MediaType.APPLICATION_JSON)
				.body(requestBody).retrieve().body(String.class);

		return response;
	}

}
