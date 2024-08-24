package com.project.roomBookingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.roomBookingSystem.entity.User;
import com.project.roomBookingSystem.enums.UserRole;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	
	User findByEmail(String email);
	
	User findByRole(UserRole role);

}
