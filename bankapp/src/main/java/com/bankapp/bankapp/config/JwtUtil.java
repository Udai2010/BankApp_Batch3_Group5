package com.bankapp.bankapp.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	@Value("${jwt.secret}")
	private String secret;

	public String generateToken(String customerId) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + 360000);

		SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

		return Jwts.builder().setSubject(customerId).setIssuedAt(now).setExpiration(expiryDate)
				.signWith(key, SignatureAlgorithm.HS256).compact();
	}

	public String extractCustomerId(String token) {
		return Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token).getBody()
				.getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
