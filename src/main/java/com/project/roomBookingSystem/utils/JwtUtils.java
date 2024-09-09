package com.project.roomBookingSystem.utils;


import java.security.Key;
import java.util.*;
import java.util.function.Function;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Configuration
public class JwtUtils {
	
	public String generateToken(UserDetails userdetails)
	{
		return generateToken(new HashMap<>(), userdetails) ;
		
	}
	public boolean isTokenValid(UserDetails userdetails,String token){
		final String username=extractUsername(token);
		return (username.equals(userdetails.getUsername()) && !isTokenExpired(token));
		
	}
	@SuppressWarnings("deprecation")
	private String generateToken(Map<String,Object>extractClaims,UserDetails userdetails) {
		return Jwts.builder().setClaims(extractClaims)
				.setSubject(userdetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*2))
				.signWith(getSignature(), SignatureAlgorithm.HS256)
                .compact();
		
	}
	private Key getSignature() {
		byte[] keyBytes = Decoders.BASE64.decode("3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b");
        return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String extractUsername(String token)
	{
		return extractClaim(token,Claims::getSubject);
	}
	private <T>T extractClaim(String token, Function<Claims,T> claimsResolver) {
		final Claims claims=extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	@SuppressWarnings({ "deprecation"})
	private Claims extractAllClaims(String token) {
		
		return Jwts.parserBuilder().setSigningKey(getSignature()).build().
                parseClaimsJws(token).getBody();
	}
	private boolean isTokenExpired(String token) {
		return extractExpirationTime(token).before(new Date());
	}
	private Date extractExpirationTime(String token) {
		
		return 	extractClaim(token,Claims::getExpiration);
	}

}

