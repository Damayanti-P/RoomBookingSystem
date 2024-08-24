package com.project.roomBookingSystem.controller;



import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.roomBookingSystem.dto.AuthenticationRequest;
import com.project.roomBookingSystem.dto.AuthenticationResponse;
import com.project.roomBookingSystem.dto.SignUpDto;
import com.project.roomBookingSystem.dto.UserDto;
import com.project.roomBookingSystem.entity.User;
import com.project.roomBookingSystem.repository.UserRepo;
import com.project.roomBookingSystem.service.AuthService;
import com.project.roomBookingSystem.service.UserService;
import com.project.roomBookingSystem.utils.JwtUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    
    private final AuthenticationManager authenticationManager;
    
    private final UserRepo userRepo;
    
    private final UserService userService;
    
    private final JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity signupUser(@RequestBody SignUpDto signupDto) {

        try {
            UserDto user = authService.createUser(signupDto);
            return new ResponseEntity(user, HttpStatus.CREATED);

        } catch (EntityExistsException e) {
            return new ResponseEntity("Email already exists", HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity("User not created", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    @PostMapping("/login")
    public AuthenticationResponse createToken(@RequestBody AuthenticationRequest authenticationRequest)
    {
    	AuthenticationResponse authenticationResponse=new AuthenticationResponse();
    	try {
    		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
    		UserDetails userDetails=userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
    		User user=userRepo.findByEmail(userDetails.getUsername());
    		final String jwtToken=jwtUtils.generateToken(userDetails);
    		authenticationResponse.setJwt(jwtToken);
            authenticationResponse.setUserRole(user.getRole());
            authenticationResponse.setUserId(user.getId());
    		
    	}catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Incorrect username or password");
        } catch (Exception ex) {
            System.out.println("Exception ex" + ex);
        }
		return authenticationResponse;
    	
    }


}
