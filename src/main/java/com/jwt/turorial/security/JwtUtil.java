package com.jwt.turorial.security;

import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.jwt.turorial.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private long expiry = 900000L;
	private String secret = "secretkey";

	public String generateToken(User user) {
		
		HashMap<String, Object> map = new HashMap<>();
		return Jwts.builder()
				.setClaims(map)
				.setSubject(user.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expiry))	
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
		
	}

	public boolean validate(String token) {
		// TODO Auto-generated method stub
		try {
			
			//isTokenExpired() && isUserNameValid()
			Claims clamis = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
		
	
	}

	public String getUsername(String token) {
		// TODO Auto-generated method stub
		System.out.println(Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject());
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}
}
