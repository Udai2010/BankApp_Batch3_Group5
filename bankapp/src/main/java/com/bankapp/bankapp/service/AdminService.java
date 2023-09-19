package com.bankapp.bankapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.bankapp.model.Admin;
import com.bankapp.bankapp.model.AdminLogin;
import com.bankapp.bankapp.model.Customer;
import com.bankapp.bankapp.model.Login;
import com.bankapp.bankapp.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	AdminRepository adminRepo;
	
	public Admin createAdmin(Admin a) {
		Admin obj = adminRepo.save(a);
		return obj;
		
	}

	public String validateAdmin(AdminLogin l) {
		String result="Invalid Credentials";
		
		Optional<Admin> obj = adminRepo.findById(l.getAdmin_id());
		if(obj.isPresent())
			{
				 if(obj.get().getPassword().equals(l.getPassword()))
				{
					result="Valid Credentials";
				}
			}
		return result;
	}
}
