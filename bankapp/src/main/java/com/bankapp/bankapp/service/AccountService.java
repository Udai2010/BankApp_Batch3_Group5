package com.bankapp.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.bankapp.model.Account;
import com.bankapp.bankapp.model.Customer;
import com.bankapp.bankapp.repository.AccountRepository;
import com.bankapp.bankapp.repository.CustomerRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepo;
	@Autowired
	CustomerRepository customerRepo;
	
	public Account createAccount(Account account, Long userID) {
		Customer obj = customerRepo.findById(userID).get();
		account.setCustomer(obj);
		return accountRepo.save(account);
		
	}
	
}
