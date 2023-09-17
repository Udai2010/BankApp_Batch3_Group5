package com.bankapp.bankapp.controller;

import javax.validation.Valid;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import com.bankapp.bankapp.model.Account;
import com.bankapp.bankapp.model.Customer;
import com.bankapp.bankapp.model.Login;
import com.bankapp.bankapp.service.CustomerService;

@RestController
@CrossOrigin("*")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/test")
	public String testMessage() {
		return "Controller is working";
	}
	
	@PostMapping("/createCustomer")
	public List<String> createNewCustomer(@RequestBody Customer c) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		jakarta.validation.Validator validator = factory.getValidator();
		Set<ConstraintViolation<Customer>> violations = validator.validate(c);

		List<String> resp = new ArrayList<String>();
		if(violations.size() == 0){
			customerService.createNewCustomer(c);
			resp.add("Customer created succesfully");
			return resp;
		}

		for(ConstraintViolation<Customer> t: violations){
			resp.add(t.getMessage());
		}

		return resp;
		
	}
	
	@PostMapping("/login")
	public String validateUser(@Valid @RequestBody Login l) {
		return customerService.validateUser(l);
		
	}
	
	/*@GetMapping("/account/{uid}")
	public List<Account> getAccount(@PathVariable("uid") Long uid){
	List<Account> acc = new ArrayList<Account>();
	acc = customerService.getAccount(uid);
		return acc
		
	}*/

}
