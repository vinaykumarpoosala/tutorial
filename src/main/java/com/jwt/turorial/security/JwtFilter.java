package com.jwt.turorial.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jwt.turorial.service.UserService;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	JwtUtil util;
	
	@Autowired
	UserService service;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String authorizationToken = request.getHeader("Authorization");
		
		if(authorizationToken != null && authorizationToken.startsWith("Bearer ")) {
			
			String token = authorizationToken.substring(7);
			System.out.println(token);
			
			if(util.validate(token)) {
				
				/*
				 * 
				 */
				UserDetails user = service.loadUserByUsername(util.getUsername(token));
				
				Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(),user.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
			
		}
		
		filterChain.doFilter(request, response);
		
	}

}
