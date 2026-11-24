package com.aeroplaneBooking.Service.Impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.aeroplaneBooking.Dtos.BookingRequestDto;
import com.aeroplaneBooking.Dtos.BookingResponseDto;
import com.aeroplaneBooking.Dtos.GetRateDto;
import com.aeroplaneBooking.Dtos.PassengerDto;
import com.aeroplaneBooking.Enum.BookingStatus;
import com.aeroplaneBooking.Enum.PaymentStatus;
import com.aeroplaneBooking.Repositories.BookingRepository;
import com.aeroplaneBooking.Repositories.UserRepository;
import com.aeroplaneBooking.Service.BookingService;
import com.aeroplaneBooking.Service.FlightServie;
import com.aeroplaneBooking.entities.BookingEntity;
import com.aeroplaneBooking.entities.PassengerEntity;
import com.aeroplaneBooking.entities.UserEntity;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private FlightServie flightServie;

	@Autowired
	@Qualifier("exchangeRateRestClient")
	private RestClient exchangeRateRestClient;

	@Override
	public BookingResponseDto createBooking(Integer userId, BookingRequestDto request) {
		UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		String flight = flightServie.getFlightById(request.getFlightId());

		BigDecimal amountInINR = null;
		BigDecimal baseAmt = null;
		BigDecimal taxAmt = null;

		try {
			JsonNode root = objectMapper.readTree(flight);
			JsonNode data = root.path("data");
			String totalAmount = data.path("total_amount").stringValue();
			String baseAmount = data.path("base_amount").stringValue();
			String taxAmount = data.path("tax_amount").stringValue();
			String totalCurrency = data.path("total_currency").stringValue();
			String exchangeRateResponse = exchangeRateRestClient.get()
					.uri("/v1/latest?base={base}&symbols=INR", totalCurrency).retrieve().body(String.class);

			BigDecimal exchangeRate = objectMapper.readTree(exchangeRateResponse).path("rates").path("INR")
					.decimalValue();

			BigDecimal amount = new BigDecimal(totalAmount);
			amountInINR = amount.multiply(exchangeRate).setScale(2, RoundingMode.HALF_UP);

			BigDecimal base = new BigDecimal(baseAmount);
			baseAmt = base.multiply(exchangeRate).setScale(2, RoundingMode.HALF_UP);

			BigDecimal tax = new BigDecimal(taxAmount);
			taxAmt = tax.multiply(exchangeRate).setScale(2, RoundingMode.HALF_UP);
		} catch (Exception e) {
			throw new RuntimeException("Unable to parse Duffel response", e);
		}

		BookingEntity booking = new BookingEntity();
		booking.setFlightId(request.getFlightId());
		booking.setEmail(request.getEmail());
		booking.setPhoneNumber(request.getPhoneNumber());
		booking.setBookingStatus(BookingStatus.PENDING);
		booking.setPaymentStatus(PaymentStatus.PENDING);
		booking.setTotalAmount(amountInINR);
		booking.setCurrency("INR");
		booking.setUser(user);
		List<PassengerDto> passenger = request.getPassenger();

		for (PassengerDto passengerDto : passenger) {
			PassengerEntity p = new PassengerEntity();
			p.setFirstName(passengerDto.getFirstName());
			p.setLastName(passengerDto.getLastName());
			p.setGender(passengerDto.getGender());
			p.setDateOfBirth(passengerDto.getDateOfBirth());
			p.setPassportNumber(passengerDto.getPassportNumber());
			p.setPassportExpiryDate(passengerDto.getPassportExpiryDate());
			p.setNationality(passengerDto.getNationality());
			p.setBooking(booking);
			booking.getPassenger().add(p);
		}

		BookingEntity savedBooking = bookingRepository.save(booking);

		BookingResponseDto responseDto = modelMapper.map(savedBooking, BookingResponseDto.class);
		responseDto.setUserId(savedBooking.getUser().getUserId());
		responseDto.setTotalAmount(savedBooking.getTotalAmount());
		responseDto.setCurrency(savedBooking.getCurrency());
		responseDto.setBaseAmount(baseAmt);
		responseDto.setTaxAmount(taxAmt);

		return responseDto;
	}

	@Override
	public GetRateDto getRate(String flightId) {

		String flight = flightServie.getFlightById(flightId);

		BigDecimal amountInINR = null;
		BigDecimal baseAmt = null;
		BigDecimal taxAmt = null;

		try {
			JsonNode root = objectMapper.readTree(flight);
			JsonNode data = root.path("data");
			String totalAmount = data.path("total_amount").stringValue();
			String baseAmount = data.path("base_amount").stringValue();
			String taxAmount = data.path("tax_amount").stringValue();
			String totalCurrency = data.path("total_currency").stringValue();
			String exchangeRateResponse = exchangeRateRestClient.get()
					.uri("/v1/latest?base={base}&symbols=INR", totalCurrency).retrieve().body(String.class);

			BigDecimal exchangeRate = objectMapper.readTree(exchangeRateResponse).path("rates").path("INR")
					.decimalValue();

			BigDecimal amount = new BigDecimal(totalAmount);
			amountInINR = amount.multiply(exchangeRate).setScale(2, RoundingMode.HALF_UP);

			BigDecimal base = new BigDecimal(baseAmount);
			baseAmt = base.multiply(exchangeRate).setScale(2, RoundingMode.HALF_UP);

			BigDecimal tax = new BigDecimal(taxAmount);
			taxAmt = tax.multiply(exchangeRate).setScale(2, RoundingMode.HALF_UP);
		} catch (Exception e) {
			throw new RuntimeException("Unable to parse Duffel response", e);
		}

		GetRateDto response = new GetRateDto();
		response.setBaseAmount(baseAmt);
		response.setTaxAmount(taxAmt);
		response.setTotalAmount(amountInINR);

		return response;
	}

}
