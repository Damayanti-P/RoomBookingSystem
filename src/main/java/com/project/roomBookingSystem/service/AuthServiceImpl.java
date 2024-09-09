package com.project.roomBookingSystem.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.roomBookingSystem.dto.SignUpDto;
import com.project.roomBookingSystem.dto.UserDto;
import com.project.roomBookingSystem.entity.User;
import com.project.roomBookingSystem.enums.UserRole;
import com.project.roomBookingSystem.repository.UserRepo;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{
	
	private final UserRepo userRepo;
	
	@PostConstruct
	public void createAdminAccount(){
		User admin=userRepo.findByRole(UserRole.ADMIN);
		if(admin== null){
			User user=new User();
			user.setEmail("admin@booking.com");
			user.setName("Admin");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			user.setRole(UserRole.ADMIN);
			userRepo.save(user);
		}else {
			System.out.println("Admin Already Exists");
		}
		
	}
	
	public UserDto createUser(SignUpDto signup){
		if(userRepo.findByEmail(signup.getEmail())!=null){
			throw new EntityExistsException("Email already Exists!!!"+ signup.getEmail());
		}
		User user=new User();
		user.setEmail(signup.getEmail());
		user.setName(signup.getName());
		user.setPassword(new BCryptPasswordEncoder().encode(signup.getPassword()));
		user.setRole(UserRole.CUSTOMER);
		User createdUser=userRepo.save(user);
		return createdUser.getUserDto();
	}
}
