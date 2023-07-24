package com.jwt.turorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.turorial.model.User;
import com.jwt.turorial.repo.UserRepo;
import com.jwt.turorial.security.JwtUtil;

@RestController
@RequestMapping("public-api/v1")
public class PublicController {
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	UserRepo repo;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@GetMapping("/test")
	public ResponseEntity<?> testApi(){
		
		return new ResponseEntity<>("Test working",HttpStatus.OK);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody User user){
		
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			
		}catch (Exception e) {
			
			new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		User userDetails = repo.findByUsername(user.getUsername()).get();
		
		String token = jwtUtil.generateToken(userDetails);
		
		return new ResponseEntity<String>(token , HttpStatus.OK);
		
	}
	
}
