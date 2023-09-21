package com.bankapp.bankapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		String result="test";
		
		Optional<Customer> obj = customerRepo.findById(l.getUsername());
		if(obj==null)
			{
				result = "Invalid User";
				return result;
			}
			else if(obj.get().getPassword().equals(l.getPassword()))
			{
				result="Valid User";
			}
			else
			{
				result="Invalid User";
			}
		return result;
	}

	public Customer getCustomer(Long customer_id){
		return customerRepo.findById(customer_id).get();
	}
	@Transactional
	public String changePassword(Login l) {
		String result = "Unsuccessful Password Update";
		customerRepo.changePassword(l.getPassword(),l.getUserID());
		
		if(customerRepo.findById(l.getUserID()).get().getPassword().equals(l.getPassword()))
		{		
			result="Password updated successfully";
				return result;
		}
		return result;
	}
	
	@Transactional
	public String forgotPassword(Login l) {
		String result = "Unsuccessful Password Update";
		if(l.getOtp()!=1234) {
			result="OTP entered is wrong";
			return result;
		}
		
		if(!customerRepo.findById(l.getUserID()).isPresent()){
			result="Invalid Credentials";
			return result;
		}
		
		int a =customerRepo.changePassword(l.getPassword(),l.getUserID());
		
		if(a!=0)
		{		
			result="Password updated successfully";
				return result;
		}
		return result;
	}
}
