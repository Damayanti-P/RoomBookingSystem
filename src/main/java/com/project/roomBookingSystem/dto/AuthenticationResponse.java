package com.project.roomBookingSystem.dto;

import com.project.roomBookingSystem.enums.UserRole;

import lombok.Data;

@Data
public class AuthenticationResponse {
	
	private String jwt;
	
	private int userId;
	
	private UserRole userRole;

}
