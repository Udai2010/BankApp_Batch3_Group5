package com.bankapp.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.bankapp.model.Customer;
import com.bankapp.bankapp.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/test")
	public String testMessage() {
		return "Controller is working";
	}
	
	@PostMapping("/createCustomer")
	public Customer createNewCustomer(@RequestBody Customer c) {
		Customer obj = customerService.createNewCustomer(c);
		return obj;
		
	}

}
