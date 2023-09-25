package com.bankapp.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.bankapp.bankapp.model.AdminLogin;

import com.bankapp.bankapp.model.Customer;
import com.bankapp.bankapp.service.AccountService;
import com.bankapp.bankapp.service.AdminService;
import com.bankapp.bankapp.service.CustomerService;

import com.bankapp.bankapp.model.Transaction;
import com.bankapp.bankapp.model.UserAccess;
import com.bankapp.bankapp.model.Admin;

@RestController
@CrossOrigin("*")
public class AdminController {

	@Autowired
	AdminService adminService;
	@Autowired
	CustomerService custService;
	
	@Autowired
	AccountService accService;
	
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
	
	@GetMapping("searchUser/{uid}")
		public Customer serach(@PathVariable("uid") Long userid) {
			return custService.getCustomer(userid);
		}
	

    @GetMapping("/admingettransactions/{uid}")
    public List<Transaction> getTransactions(@PathVariable("uid") Long customer_id){
        return adminService.getTransactions(customer_id);
    }
    
    @GetMapping("/getStatus/{uid}")
    public String getstatus(@PathVariable("uid") Long customer_id) {
    	return accService.getStatus(customer_id);
    }
    
    @PostMapping("/enable-disable")
    public String UserAcess(@RequestBody UserAccess User) {
        accService.Access(User.getAccess(),User.getCustomerId());
    	
    	String s = User.getAccess();
    	if(s == "active") {
			return "Account activated successfully";
		}
		
		else {
			return "Account de-activated successfully";
		}
    	
    }

}
