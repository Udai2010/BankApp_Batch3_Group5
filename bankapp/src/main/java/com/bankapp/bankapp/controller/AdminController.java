package com.bankapp.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.bankapp.bankapp.model.AdminLogin;
import com.bankapp.bankapp.model.Transaction;
import com.bankapp.bankapp.service.AdminService;
import com.bankapp.bankapp.model.Admin;

@RestController
@CrossOrigin("*")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@PostMapping("/createAdmin")
	public String createAdmin(@RequestBody Admin a) {
		
		String resp;
		Admin obj = adminService.createAdmin(a);
		if(obj!=null) {
			resp="Admin Created Successfully";
			return resp;
		}
		
		resp="Admin Creation Failed";
		return resp;
	} 
	
	@PostMapping("/adminLogin")
	public String validateAdmin(@RequestBody AdminLogin l) {
		return adminService.validateAdmin(l);
	}

    @GetMapping("/admingettransactions/{uid}")
    public List<Transaction> getTransactions(@PathVariable("uid") Long customer_id){
        return adminService.getTransactions(customer_id);
    }

}
