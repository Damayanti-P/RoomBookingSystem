package com.project.roomBookingSystem.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.roomBookingSystem.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepo userRepo;

	@Override
	public UserDetailsService userDetailsService() {
			return new UserDetailsService() {
				
				@Override
				public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
					
					return userRepo.findByEmail(email);
				}
			};
		}
	
}
