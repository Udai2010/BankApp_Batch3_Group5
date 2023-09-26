package com.bankapp.bankapp.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bankapp.bankapp.model.Account;
import com.bankapp.bankapp.model.Customer;
import com.bankapp.bankapp.model.FundTransfer;
import com.bankapp.bankapp.model.Withdraw;
import com.bankapp.bankapp.service.AccountService;
import com.bankapp.bankapp.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
// import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AccountController.class})
@ExtendWith(SpringExtension.class)
class AccountControllerTest {
    @Autowired
    private AccountController accountController;

    @MockBean
    private AccountService accountService;

    @MockBean
    private CustomerService customerService;


    // @Test
    // void testWithdrawAccount3() throws Exception {
    //     AccountController accountController = new AccountController();
    //     Withdraw withdraw = mock(Withdraw.class);
    //     when(withdraw.getAmount()).thenReturn(500.0d);
    //     when(withdraw.getAccount_id()).thenReturn(3L);
    //     AccountService mockAccountService = Mockito.mock(AccountService.class);
    //     when(mockAccountService.withdrawAmount(withdraw.getAmount(), withdraw.getAccount_id(), false))
    //     .thenReturn("Withdraw successful");
        
    //     String content="{\"account_id\":3, \"amount\":500}";
    //     MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
    //     .put("/withdraw")
    //     .contentType(MediaType.APPLICATION_JSON)
    //     .content(content);

    //     MockMvcBuilders.standaloneSetup(accountController)
    //             .build()
    //             .perform(requestBuilder)
    //             .andExpect(MockMvcResultMatchers.status().isOk())
    //             .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
    //             .andExpect(MockMvcResultMatchers.content().string("Withdraw successful"));
        
        
    // }

    /**
     * Method under test: {@link AccountController#Balance(Long)}
     */
    @Test
    void testBalance() throws Exception {
        when(accountService.getBalance(Mockito.<Long>any())).thenReturn(10.0d);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/balance/{accId}", 1L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("10.0"));
    }
    /**
     * Method under test: {@link AccountController#createNewAccount(Account, Long)}
     */
    @Test
    void testCreateNewAccount() throws Exception {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setCustomer_Id(1L);
        customer.setDob("Dob");
        customer.setEmail("jane.doe@example.org");
        customer.setFathername("Fathername");
        customer.setMothername("Mothername");
        customer.setName("Name");
        customer.setPan_number("42");
        customer.setPassword("iloveyou");

        Account account = new Account();
        account.setAccount_id(1L);
        account.setAccount_type("3");
        account.setAnnual_income(10.0d);
        account.setBalance(10.0d);
        account.setBranch("janedoe/featurebranch");
        account.setCustomer(customer);
        account.setDebit_card("Debit card");
        account.setIFSC("I FSC");
        account.setIncome_source("Income source");
        account.setNet_banking("Net banking");
        account.setOccupation_type("Occupation type");
        account.setStatus("Status");
        when(accountService.createAccount(Mockito.<Account>any(), Mockito.<Long>any())).thenReturn(account);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setCustomer_Id(1L);
        customer2.setDob("Dob");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFathername("Fathername");
        customer2.setMothername("Mothername");
        customer2.setName("Name");
        customer2.setPan_number("42");
        customer2.setPassword("iloveyou");

        Account account2 = new Account();
        account2.setAccount_id(1L);
        account2.setAccount_type("3");
        account2.setAnnual_income(10.0d);
        account2.setBalance(10.0d);
        account2.setBranch("janedoe/featurebranch");
        account2.setCustomer(customer2);
        account2.setDebit_card("Debit card");
        account2.setIFSC("I FSC");
        account2.setIncome_source("Income source");
        account2.setNet_banking("Net banking");
        account2.setOccupation_type("Occupation type");
        account2.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(account2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/createAccount/{uid}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[\"Your new Account ID is 1\"]"));
    }

    /**
     * Method under test: {@link AccountController#createNewAccount(Account, Long)}
     */
    @Test
    void testCreateNewAccount2() throws Exception {
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setCustomer_Id(1L);
        customer.setDob("Dob");
        customer.setEmail("jane.doe@example.org");
        customer.setFathername("Fathername");
        customer.setMothername("Mothername");
        customer.setName("Name");
        customer.setPan_number("42");
        customer.setPassword("iloveyou");

        Account account = new Account();
        account.setAccount_id(1L);
        account.setAccount_type("3");
        account.setAnnual_income(10.0d);
        account.setBalance(10.0d);
        account.setBranch("janedoe/featurebranch");
        account.setCustomer(customer);
        account.setDebit_card("Debit card");
        account.setIFSC("I FSC");
        account.setIncome_source("Income source");
        account.setNet_banking("Net banking");
        account.setOccupation_type("Occupation type");
        account.setStatus("Status");
        when(accountService.createAccount(Mockito.<Account>any(), Mockito.<Long>any())).thenReturn(account);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setCustomer_Id(1L);
        customer2.setDob("Dob");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFathername("Fathername");
        customer2.setMothername("Mothername");
        customer2.setName("Name");
        customer2.setPan_number("42");
        customer2.setPassword("iloveyou");

        Account account2 = new Account();
        account2.setAccount_id(1L);
        account2.setAccount_type(null);
        account2.setAnnual_income(10.0d);
        account2.setBalance(10.0d);
        account2.setBranch("janedoe/featurebranch");
        account2.setCustomer(customer2);
        account2.setDebit_card("Debit card");
        account2.setIFSC("I FSC");
        account2.setIncome_source("Income source");
        account2.setNet_banking("Net banking");
        account2.setOccupation_type("Occupation type");
        account2.setStatus("Status");
        String content = (new ObjectMapper()).writeValueAsString(account2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/createAccount/{uid}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[\"Account type can not be blank.\"]"));
    }

    /**
     * Method under test: {@link AccountController#getAccount(Long)}
     */
    @Test
    void testGetAccount() throws Exception {
        when(accountService.getAllAccounts(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/account/{id}", 1L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

