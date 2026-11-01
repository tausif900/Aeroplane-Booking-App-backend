package com.aeroplaneBooking.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aeroplaneBooking.Dtos.LoginRequestDto;
import com.aeroplaneBooking.Dtos.RegistrationRequestDto;
import com.aeroplaneBooking.Dtos.UserDto;
import com.aeroplaneBooking.Enum.UserRole;
import com.aeroplaneBooking.Repositories.UserRepository;
import com.aeroplaneBooking.Service.UserService;
import com.aeroplaneBooking.entities.UserEntity;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;

//	Registration of a User
	@Override
	public UserDto registerUser(RegistrationRequestDto request) {

		boolean userAlreadyExists = userRepository.existsByEmail(request.getEmail());

		if (userAlreadyExists) {
			throw new RuntimeException("User Already Exist by this email");
		}

		UserEntity user = new UserEntity();
		user.setUserName(request.getUserName());
		user.setEmail(request.getEmail());
		user.setRole(UserRole.ROLE_User);
		String hashedPassword = passwordEncoder.encode(request.getPassword());
		user.setPassword(hashedPassword);

		UserEntity savedUser = userRepository.save(user);

		UserDto userDto = modelMapper.map(savedUser, UserDto.class);
		userDto.setUserName(request.getUserName());

		return userDto;
	}

	@Override
	public UserDto loginUser(LoginRequestDto request) {
		UserEntity user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("User not Found"));
		boolean isPasswordValid = passwordEncoder.matches(request.getPassword(), user.getPassword());
		if (!isPasswordValid) {
			throw new RuntimeException("Password is incorrect");
		}
		return modelMapper.map(user, UserDto.class);
	}

}
