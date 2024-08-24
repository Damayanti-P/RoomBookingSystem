package com.project.roomBookingSystem.dto;

import com.project.roomBookingSystem.enums.UserRole;

import lombok.Data;

@Data
public class UserDto {
	
	private int id;
	
	private String name;
	
	private String email;

	private UserRole userRole;
}
