package com.bankapp.bankapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.bankapp.model.Customer;
import com.bankapp.bankapp.model.Login;
import com.bankapp.bankapp.service.CustomerService;

@RestController
@CrossOrigin("*")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/test")
	public String testMessage() {
		return "Controller is working";
	}
	
	@PostMapping("/createCustomer")
<<<<<<< Updated upstream
	public Customer createNewCustomer(@RequestBody Customer c) {
		Customer obj = customerService.createNewCustomer(c);
		return obj;
=======
	public List<String> createNewCustomer(@RequestBody Customer c) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		jakarta.validation.Validator validator = factory.getValidator();
		Set<ConstraintViolation<Customer>> violations = validator.validate(c);

		List<String> resp = new ArrayList<String>();
		if(violations.size() == 0){
			Customer obj = customerService.createNewCustomer(c);
			String result="Your new Customer ID is "+obj.getCustomer_Id().toString();
			resp.add(result);
			return resp;
		}

		for(ConstraintViolation<Customer> t: violations){
			resp.add(t.getMessage());
		}

		return resp;
>>>>>>> Stashed changes
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> validateUser(@Valid @RequestBody Login l) {
		String res=customerService.validateUser(l);
		if(res.equals("Invalid User")) return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(res,HttpStatus.OK);
		
	}

}
