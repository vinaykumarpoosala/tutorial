package com.jwt.turorial.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class PrivateController {

	@GetMapping("/test")
	public ResponseEntity<?> testApi(){
		
		return new ResponseEntity<>("Logged in user is  "+SecurityContextHolder.getContext().getAuthentication().getPrincipal(),HttpStatus.OK);
	}
}
