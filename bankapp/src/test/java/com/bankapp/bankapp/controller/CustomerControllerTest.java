package com.bankapp.bankapp.controller;

import static org.mockito.Mockito.when;

import com.bankapp.bankapp.model.Customer;
import com.bankapp.bankapp.model.Login;
import com.bankapp.bankapp.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@ContextConfiguration(classes = {CustomerController.class})
@ExtendWith(SpringExtension.class)
class CustomerControllerTest {
    @Autowired
    private CustomerController customerController;

    @MockBean
    private CustomerService customerService;

    @Test
    void testChagnePassword() throws Exception {
        when(customerService.changePassword(Mockito.<Login>any())).thenReturn("iloveyou");

        Login login = new Login();
        login.setOtp(1L);
        login.setPassword("iloveyou");
        login.setUserID(1L);
        login.setUsername(1L);
        String content = (new ObjectMapper()).writeValueAsString(login);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/changepassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("iloveyou"));
    }

    @Test
    void testCreateNewCustomer2() throws Exception {
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
        when(customerService.createNewCustomer(Mockito.<Customer>any())).thenReturn(customer);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setCustomer_Id(1L);
        customer2.setDob("Dob");
        customer2.setEmail("jane.doe@example.org");
        customer2.setFathername("Fathername");
        customer2.setMothername("Mothername");
        customer2.setName("Name");
        customer2.setPan_number("Pan number");
        customer2.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(customer2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/createCustomer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[\"Your new Customer ID is 1\"]"));
    }

    @Test
    void testValidateUser() throws Exception {
        when(customerService.validateUser(Mockito.<Login>any())).thenReturn("2020-03-01");

        Login login = new Login();
        login.setOtp(1L);
        login.setPassword("iloveyou");
        login.setUserID(1L);
        login.setUsername(1L);
        String content = (new ObjectMapper()).writeValueAsString(login);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("2020-03-01"));
    }

    @Test
    void testForgotPassword() throws Exception {
        when(customerService.forgotPassword(Mockito.<Login>any())).thenReturn("iloveyou");

        Login login = new Login();
        login.setOtp(1L);
        login.setPassword("iloveyou");
        login.setUserID(1L);
        login.setUsername(1L);
        String content = (new ObjectMapper()).writeValueAsString(login);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/forgotpassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("iloveyou"));
    }

    @Test
    void testCreateNewCustomer() throws Exception {
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
        String content = (new ObjectMapper()).writeValueAsString(customer);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/createCustomer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[\"pan number must be 10 characters\"]"));
    }

    @Test
    void testGetUser() throws Exception {
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/{uid}", 1L);
        MockMvcBuilders.standaloneSetup(customerController)
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
    void testTestMessage() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/test");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Controller is working"));
    }

    @Test
    void testTestMessage2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/test");
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Controller is working"));
    }
}

