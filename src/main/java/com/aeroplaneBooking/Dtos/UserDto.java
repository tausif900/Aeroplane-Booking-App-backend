package com.aeroplaneBooking.Dtos;

import com.aeroplaneBooking.Enum.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private Integer userId;

	private String userName;

	private String email;

	private UserRole role;
}
