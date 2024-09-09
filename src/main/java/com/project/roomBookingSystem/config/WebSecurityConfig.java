package com.project.roomBookingSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.roomBookingSystem.enums.UserRole;
import com.project.roomBookingSystem.service.UserService;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
	
		private final UserService	 userService;
		
		private final  JwtAuthenticationFilter jwtAuthenticationFilter;
	 
		@Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

	            httpSecurity.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(request->
	             request.requestMatchers("/auth/**").permitAll()
	            .requestMatchers("/admin/**").hasAuthority(UserRole.ADMIN.name())
	            .requestMatchers("/status/**").hasAuthority(UserRole.ADMIN.name())
                .requestMatchers("/customer/**").hasAuthority(UserRole.CUSTOMER.name())
                .anyRequest().authenticated())
        		.sessionManagement(manger->manger.sessionCreationPolicy(STATELESS))
        		.authenticationProvider(authenticationProvider()).addFilterBefore(
                jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	            return httpSecurity.build();	
	    }
		
	    @Bean
	    public AuthenticationManager  authenticationManager(AuthenticationConfiguration configuration) throws Exception {
	        return configuration.getAuthenticationManager();

	    }
	    
	    @Bean
	    public AuthenticationProvider authenticationProvider() {

	        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(userService.userDetailsService());
	        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
	        return authenticationProvider;
	    }

}
