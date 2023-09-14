package com.bankapp.bankapp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.hamcrest.Matchers;
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

import com.bankapp.bankapp.model.Account;
import com.bankapp.bankapp.model.Customer;
import com.bankapp.bankapp.repository.AccountRepository;
import com.bankapp.bankapp.repository.CustomerRepository;
import com.bankapp.bankapp.service.AccountService;
import com.bankapp.bankapp.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AccountControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private AccountService accService;
	
	@MockBean
	private AccountRepository accRepo;
	
	@MockBean
	private CustomerService custService;
	private ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void testCreateAccount() throws Exception {
		Account acc = new Account();
		acc.setAccount_id((long)1);
		acc.setAccount_type("savings");
		acc.setBalance(500);
		acc.setBranch("Bangalore");
		acc.setDebit_card("Yes");
		acc.setIFSC("BLR1234");
		acc.setNet_banking("No");
		acc.setStatus("Active");
		long customerID = 2;
				
		Mockito.when(accService.createAccount(acc,customerID))
		.thenReturn(acc);
		String json = mapper.writeValueAsString(acc);
		String url = "/createAccount/"+customerID;
		mvc.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", Matchers.equalTo("Account created succesfully")));
	}
}
