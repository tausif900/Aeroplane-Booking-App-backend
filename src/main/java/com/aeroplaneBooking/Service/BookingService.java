package com.aeroplaneBooking.Service;

import com.aeroplaneBooking.Dtos.BookingRequestDto;
import com.aeroplaneBooking.Dtos.BookingResponseDto;

public interface BookingService {

	BookingResponseDto createBooking(Integer userId, BookingRequestDto request);

}
