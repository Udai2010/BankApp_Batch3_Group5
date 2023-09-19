package com.bankapp.bankapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.bankapp.model.Admin;
import com.bankapp.bankapp.model.AdminLogin;
import com.bankapp.bankapp.service.AdminService;


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
}
