package com.bankapp.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.bankapp.model.Login;
import com.bankapp.bankapp.service.CustomerService;
import com.bankapp.bankapp.config.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody Login l) {
		if (customerService.validateUser(l)) {
			String CustomerId = String.valueOf(l.getUserID());
			final String token = jwtUtil.generateToken(CustomerId);
			return ResponseEntity.ok().body(token);
		} else {
			return ResponseEntity.badRequest().body("Authentication Failed");
		}
	}

}
