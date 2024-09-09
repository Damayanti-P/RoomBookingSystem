package com.project.roomBookingSystem.config;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.roomBookingSystem.service.UserService;
import com.project.roomBookingSystem.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtUtils jwtUtil;
	
	private final UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		{

	        final  String authHeader = request.getHeader("Authorization");
	        if(StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
        filterChain.doFilter(request, response);
        return;
    }

    String jwtToken = authHeader.substring(7);
    String email = jwtUtil.extractUsername(jwtToken);
    if(!email.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(email);

        if(jwtUtil.isTokenValid(userDetails, jwtToken)) {
            SecurityContext context = SecurityContextHolder.createEmptyContext();

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
                    );

            token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            context.setAuthentication(token);
            SecurityContextHolder.setContext(context);
        }
    }
    filterChain.doFilter(request, response);

}
	
}
}
