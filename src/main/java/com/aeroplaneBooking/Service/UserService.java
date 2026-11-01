package com.aeroplaneBooking.Service;

import com.aeroplaneBooking.Dtos.LoginRequestDto;
import com.aeroplaneBooking.Dtos.RegistrationRequestDto;
import com.aeroplaneBooking.Dtos.UserDto;

public interface UserService {

	UserDto registerUser(RegistrationRequestDto request);

	UserDto loginUser(LoginRequestDto request);

}
