package com.product.ojinserver.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.ojinserver.configuration.JwtTokenUtil;
import com.product.ojinserver.dto.AuthRequest;
import com.product.ojinserver.dto.UserView;
import com.product.ojinserver.entity.User;

@RestController
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenUtil jwtTokenUtil;

	public AuthenticationController(AuthenticationManager _authenticationManager, JwtTokenUtil _jwtTokenUtil) {
		this.authenticationManager = _authenticationManager;
		this.jwtTokenUtil = _jwtTokenUtil;
	}

	@PostMapping("login")
	public ResponseEntity<UserView> login(@RequestBody AuthRequest request) {
		try {
			Authentication authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

			User user = (User) authenticate.getPrincipal();

			return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
					.body(new UserView(user));
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}
