package com.aeroplaneBooking.Service;

import com.aeroplaneBooking.Dtos.BookingRequestDto;
import com.aeroplaneBooking.Dtos.BookingResponseDto;
import com.aeroplaneBooking.Dtos.GetRateDto;

public interface BookingService {

	BookingResponseDto createBooking(Integer userId, BookingRequestDto request);

	GetRateDto getRate(String flightId);

}
