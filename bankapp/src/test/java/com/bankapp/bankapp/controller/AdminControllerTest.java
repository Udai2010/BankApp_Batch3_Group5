package com.bankapp.bankapp.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.bankapp.bankapp.model.Admin;
import com.bankapp.bankapp.model.AdminLogin;
import com.bankapp.bankapp.model.Customer;
import com.bankapp.bankapp.model.UserAccess;
import com.bankapp.bankapp.service.AccountService;
import com.bankapp.bankapp.service.AdminService;
import com.bankapp.bankapp.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AdminController.class})
@ExtendWith(SpringExtension.class)
class AdminControllerTest {
    @MockBean
    private AccountService accountService;

    @Autowired
    private AdminController adminController;

    @MockBean
    private AdminService adminService;

    @MockBean
    private CustomerService customerService;

    @Test
    void testCreateAdmin() throws Exception {
        Admin admin = new Admin();
        admin.setAdmin_id(1L);
        admin.setDob("Dob");
        admin.setEmail("jane.doe@example.org");
        admin.setName("Name");
        admin.setPan_number("42");
        admin.setPassword("iloveyou");
        when(adminService.createAdmin(Mockito.<Admin>any())).thenReturn(admin);

        Admin admin2 = new Admin();
        admin2.setAdmin_id(1L);
        admin2.setDob("Dob");
        admin2.setEmail("jane.doe@example.org");
        admin2.setName("Name");
        admin2.setPan_number("42");
        admin2.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(admin2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/createAdmin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Admin Created Successfully"));
    }

    @Test
    void testValidateAdmin() throws Exception {
        when(adminService.validateAdmin(Mockito.<AdminLogin>any())).thenReturn("2020-03-01");

        AdminLogin adminLogin = new AdminLogin();
        adminLogin.setAdmin_id(1L);
        adminLogin.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(adminLogin);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/adminLogin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("2020-03-01"));
    }

    @Test
    void testGetTransactions() throws Exception {
        when(adminService.getTransactions(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admingettransactions/{uid}", 1L);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetstatus() throws Exception {
        when(accountService.getStatus(Mockito.<Long>any())).thenReturn("Status");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getStatus/{uid}", 1L);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Status"));
    }

    @Test
    void testSerach() throws Exception {
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
        when(customerService.getCustomer(Mockito.<Long>any())).thenReturn(customer);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/searchUser/{uid}", 1L);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"customer_Id\":1,\"name\":\"Name\",\"email\":\"jane.doe@example.org\",\"pan_number\":\"42\",\"password\":\"iloveyou"
                                        + "\",\"dob\":\"Dob\",\"fathername\":\"Fathername\",\"mothername\":\"Mothername\",\"address\":\"42 Main St\"}"));
    }

    @Test
    void testUserAcess() throws Exception {
        doNothing().when(accountService).Access(Mockito.<String>any(), Mockito.<Long>any());

        UserAccess userAccess = new UserAccess();
        userAccess.setAccess("Access");
        userAccess.setCustomerId(1L);
        String content = (new ObjectMapper()).writeValueAsString(userAccess);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/enable-disable")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Account de-activated successfully"));
    }
}

