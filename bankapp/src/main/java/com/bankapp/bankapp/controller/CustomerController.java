package com.bankapp.bankapp.controller;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import java.util.Set;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import com.bankapp.bankapp.exception.InvalidCredentialException;
import com.bankapp.bankapp.model.Account;
import com.bankapp.bankapp.model.Customer;
import com.bankapp.bankapp.model.Login;
import com.bankapp.bankapp.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/test")
	public String testMessage() {
		return "Controller is working";
	}
	
	@PostMapping("/auth/createCustomer")
	public ResponseEntity<List<String>> createNewCustomer(@RequestBody Customer c) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		javax.validation.Validator validator = factory.getValidator();
		Set<ConstraintViolation<Customer>> violations = validator.validate(c);

		List<String> resp = new ArrayList<String>();
		if(violations.size() == 0){

			Customer obj = customerService.createNewCustomer(c);
			String result="Your new Customer ID is "+obj.getCustomer_Id().toString();
			resp.add(result);

			return ResponseEntity.ok().body(resp);
			}

		for(ConstraintViolation<Customer> t: violations){
			resp.add(t.getMessage());
		}

		return ResponseEntity.badRequest().body(resp);

		
	}

	@GetMapping("/user/{uid}")
	public Customer getUser(@PathVariable("uid") Long uid){
		return customerService.getCustomer(uid);
	}
	
	@PutMapping("/changepassword")
	public String chagnePassword(@RequestBody Login l){
		return customerService.changePassword(l);
	}
	
	@PutMapping("/forgotpassword")
	public String forgotPassword(@RequestBody Login l) {
		return customerService.forgotPassword(l);
	}
	
	

	
	/*@GetMapping("/account/{uid}")
	public List<Account> getAccount(@PathVariable("uid") Long uid){
	List<Account> acc = new ArrayList<Account>();
	acc = customerService.getAccount(uid);
		return acc
		
	}*/

}
