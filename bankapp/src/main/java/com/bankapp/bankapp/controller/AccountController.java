package com.bankapp.bankapp.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


import com.bankapp.bankapp.model.Account;
import com.bankapp.bankapp.repository.CustomerRepository;
import com.bankapp.bankapp.service.AccountService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

import com.bankapp.bankapp.repository.CustomerRepository;


@RestController
@CrossOrigin("*")
public class AccountController {
    @Autowired
    AccountService accountService;
    
	@PostMapping("/createAccount/{uid}")
	public List<String> createNewAccount(@RequestBody Account account,@PathVariable("uid") Long userID) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		jakarta.validation.Validator validator = factory.getValidator();
		Set<ConstraintViolation<Account>> violations = validator.validate(account);

		List<String> resp = new ArrayList<String>();
		if(violations.size() == 0){
			Account acc = accountService.createAccount(account, userID);
			System.out.print(acc.toString());
			if(acc!=null)
			{	resp.add("Account created succesfully");
				return resp;
			}
		}

		for(ConstraintViolation<Account> t: violations){
			resp.add(t.getMessage());
		}
		return resp;
	}


	// @Autowired
	// AccountService accountService;
	
	// @Autowired
	// CustomerRepository customerRepo;
	// @PostMapping("/createAccount/{uid}")
	// public String createAccount(@RequestBody Account account,@PathVariable("uid") Long userID) {
		
	// 	String result="";
	// 	Account acc = accountService.createAccount(account, userID);
	// 	if(acc!=null)
	// 		result="Account Created";
			
	// 		System.out.print(acc.getCustomer().getName());
	// 	return result;
	// }


}
