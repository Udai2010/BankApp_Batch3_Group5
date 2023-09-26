package com.bankapp.bankapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankapp.bankapp.model.Customer;
import com.bankapp.bankapp.model.Login;
import com.bankapp.bankapp.repository.CustomerRepository;

@Service
public class CustomerService implements UserDetailsService {

	@Autowired
	CustomerRepository customerRepo;

	public Customer createNewCustomer(Customer c) {
		Customer obj = customerRepo.save(c);
		return obj;

	}


	public boolean validateUser(Login l) {
		Optional<Customer> obj = customerRepo.findById(l.getUsername());
		if (obj == null) {
			return false;
		} else if (obj.get().getPassword().equals(l.getPassword())) {
			return true;
		} else {
			return false;
		}

	}

	public Customer getCustomer(Long customer_id) {
		return customerRepo.findById(customer_id).get();
	}

	@Transactional
	public String changePassword(Login l) {
		String result = "Unsuccessful Password Update";
		customerRepo.changePassword(l.getPassword(), l.getUserID());

		if (customerRepo.findById(l.getUserID()).get().getPassword().equals(l.getPassword())) {
			result = "Password updated successfully";
			return result;
		}
		return result;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = getCustomer(Long.parseLong(username));
		if(customer == null) {
			throw new UsernameNotFoundException("User not Found with Customer Id: " + username);
		}
		return User.builder().username(Long.toString(customer.getCustomer_Id())).password(customer.getPassword()).roles("USER").build();
		
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
