package com.aeroplaneBooking.SecurityConfig.JwtFilter;

import java.util.Date;
import java.util.HexFormat;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${jwt.secretKey}")
	private String secretKey;

	@Value("${jwt.expiration}")
	private long expiration;

	public long getExpiration() {
		return expiration;
	}

	public SecretKey getSigningKey() {
		byte[] hex = HexFormat.of().parseHex(secretKey);
		SecretKey secretKey = Keys.hmacShaKeyFor(hex);
		return secretKey;
	}

	public String generateToken(String email) {
		String token = Jwts.builder().subject(email).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + expiration)).signWith(getSigningKey()).compact();
		return token;
	}

	private Claims extractAllClaims(String token) {
		Claims payload = Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
		return payload;
	}

	public String extractEmail(String token) {
		String email = extractAllClaims(token).getSubject();
		return email;
	}

	public boolean isTokenValid(String token, String email) {
		return extractEmail(token).equals(email);
	}

}
