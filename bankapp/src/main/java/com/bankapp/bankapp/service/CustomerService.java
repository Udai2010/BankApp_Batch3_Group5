package com.bankapp.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.bankapp.model.Customer;
import com.bankapp.bankapp.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository CustomerRepo;
	
	public Customer createNewCustomer(Customer c) {
		Customer obj = CustomerRepo.save(c);
		return obj;
		
	}

}
