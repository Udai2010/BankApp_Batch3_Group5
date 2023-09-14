package com.bankapp.bankapp;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import com.bankapp.bankapp.model.Customer;
import com.bankapp.bankapp.model.Login;
import com.bankapp.bankapp.repository.CustomerRepository;
import com.bankapp.bankapp.service.AccountService;
import com.bankapp.bankapp.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CustomerControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private CustomerService custService;
	
	@MockBean
	private CustomerRepository custRepo;
	
	@MockBean
	private AccountService accService;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void testCreateCustomer() throws Exception {
		Customer cust = new Customer();
		cust.setCustomer_Id((long) 1);
		cust.setDob("11/05/2001");
		cust.setAddress("HSR Layout, Sector 7");
		cust.setEmail("alan@gmail.com");
		cust.setName("Alan");
		cust.setFathername("John");
		cust.setMothername("Jane");
		cust.setPan_number("1234567890");
		cust.setPassword("abc@12345");

		List<String> resp = new ArrayList<String>();
		resp.add("Customer created succesfully");
		Mockito.when(custService.createNewCustomer(ArgumentMatchers.any()))
		.thenReturn(cust);
		String json = mapper.writeValueAsString(cust);
		MvcResult result = mvc.perform(post("/createCustomer")
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
		Assertions.assertEquals(resp.toString(), result.getResponse().getContentAsString());
	}

	@Test
	public void testLogin() throws Exception {
		Login user = new Login();
		user.setUserID((long)2);
		user.setPassword("alan@123");
		
		Mockito.when(custService.validateUser(ArgumentMatchers.any()))
		.thenReturn("Valid User");
		
		String json = mapper.writeValueAsString(user);
		MvcResult result = mvc.perform(post("/login")
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andReturn();
		Assertions.assertEquals("Valid User",result.getResponse().getContentAsString());
	}	
		
}
	

