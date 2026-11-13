package com.aeroplaneBooking.Service.Impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeroplaneBooking.Dtos.BookingRequestDto;
import com.aeroplaneBooking.Dtos.BookingResponseDto;
import com.aeroplaneBooking.Dtos.PassengerDto;
import com.aeroplaneBooking.Enum.BookingStatus;
import com.aeroplaneBooking.Enum.PaymentStatus;
import com.aeroplaneBooking.Repositories.BookingRepository;
import com.aeroplaneBooking.Repositories.UserRepository;
import com.aeroplaneBooking.Service.BookingService;
import com.aeroplaneBooking.entities.BookingEntity;
import com.aeroplaneBooking.entities.PassengerEntity;
import com.aeroplaneBooking.entities.UserEntity;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public BookingResponseDto createBooking(Integer userId, BookingRequestDto request) {
		UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		BookingEntity booking = new BookingEntity();
		booking.setFlightId(request.getFlightId());
		booking.setEmail(request.getEmail());
		booking.setPhoneNumber(request.getPhoneNumber());
		booking.setBookingStatus(BookingStatus.PENDING);
		booking.setPaymentStatus(PaymentStatus.PENDING);
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

		return responseDto;
	}

}
