package com.bankapp.bankapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.bankapp.model.Account;
import com.bankapp.bankapp.model.Customer;
import com.bankapp.bankapp.repository.CustomerRepository;
import com.bankapp.bankapp.service.AccountService;

@RestController
@CrossOrigin("*")
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@Autowired
	CustomerRepository customerRepo;
	@PostMapping("/createAccount/{uid}")
	public String createAccount(@RequestBody Account account,@PathVariable("uid") Long userID) {
		
		String result="";
		Account acc = accountService.createAccount(account, userID);
		if(acc!=null)
			result="Account Created";
			
			System.out.print(acc.getCustomer().getName());
		return result;
	}

}
