package com.bankapp.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.bankapp.model.Customer;
import com.bankapp.bankapp.model.Login;
import com.bankapp.bankapp.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepo;
	
	public Customer createNewCustomer(Customer c) {
		Customer obj = customerRepo.save(c);
		return obj;
		
	}

	public String validateUser(Login l) {
		String result="";
		//Customer c = null;
		
		Customer obj = customerRepo.findByUsername(l.getUsername());
		if(obj==null)
		{
			result = "Invalid User";
			return result;
		}
		else if(obj.getPassword().equals(l.getPassword()))
		{
			result="Valid User";
		}
		else
		{
			result="Invalid User";
		}
		return result;
	}

}
