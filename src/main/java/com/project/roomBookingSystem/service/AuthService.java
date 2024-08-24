package com.project.roomBookingSystem.service;

import com.project.roomBookingSystem.dto.SignUpDto;
import com.project.roomBookingSystem.dto.UserDto;

public interface AuthService {

	UserDto createUser(SignUpDto signupDto);

}
