package com.bankapp.bankapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bankapp.bankapp.model.Customer;
import com.bankapp.bankapp.model.Login;
import com.bankapp.bankapp.repository.CustomerRepository;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomerService.class})
@ExtendWith(SpringExtension.class)
class CustomerServiceTest {
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    /**
     * Method under test: {@link CustomerService#createNewCustomer(Customer)}
     */
    @Test
    void testCreateNewCustomer() {
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
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer);

        Customer c = new Customer();
        c.setAddress("42 Main St");
        c.setCustomer_Id(1L);
        c.setDob("Dob");
        c.setEmail("jane.doe@example.org");
        c.setFathername("Fathername");
        c.setMothername("Mothername");
        c.setName("Name");
        c.setPan_number("42");
        c.setPassword("iloveyou");
        assertSame(customer, customerService.createNewCustomer(c));
        verify(customerRepository).save(Mockito.<Customer>any());
    }

    /**
     * Method under test: {@link CustomerService#validateUser(Login)}
     */
    @Test
    void testValidateUser() {
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
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Login l = new Login();
        l.setOtp(1L);
        l.setPassword("iloveyou");
        l.setUserID(1L);
        l.setUsername(1L);
        assertEquals("Valid User", customerService.validateUser(l));
        verify(customerRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CustomerService#validateUser(Login)}
     */
    @Test
    void testValidateUser2() {
        Customer customer = mock(Customer.class);
        when(customer.getPassword()).thenReturn("foo");
        doNothing().when(customer).setAddress(Mockito.<String>any());
        doNothing().when(customer).setCustomer_Id(Mockito.<Long>any());
        doNothing().when(customer).setDob(Mockito.<String>any());
        doNothing().when(customer).setEmail(Mockito.<String>any());
        doNothing().when(customer).setFathername(Mockito.<String>any());
        doNothing().when(customer).setMothername(Mockito.<String>any());
        doNothing().when(customer).setName(Mockito.<String>any());
        doNothing().when(customer).setPan_number(Mockito.<String>any());
        doNothing().when(customer).setPassword(Mockito.<String>any());
        customer.setAddress("42 Main St");
        customer.setCustomer_Id(1L);
        customer.setDob("Dob");
        customer.setEmail("jane.doe@example.org");
        customer.setFathername("Fathername");
        customer.setMothername("Mothername");
        customer.setName("Name");
        customer.setPan_number("42");
        customer.setPassword("iloveyou");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Login l = new Login();
        l.setOtp(1L);
        l.setPassword("iloveyou");
        l.setUserID(1L);
        l.setUsername(1L);
        assertEquals("Invalid User", customerService.validateUser(l));
        verify(customerRepository).findById(Mockito.<Long>any());
        verify(customer).getPassword();
        verify(customer).setAddress(Mockito.<String>any());
        verify(customer).setCustomer_Id(Mockito.<Long>any());
        verify(customer).setDob(Mockito.<String>any());
        verify(customer).setEmail(Mockito.<String>any());
        verify(customer).setFathername(Mockito.<String>any());
        verify(customer).setMothername(Mockito.<String>any());
        verify(customer).setName(Mockito.<String>any());
        verify(customer).setPan_number(Mockito.<String>any());
        verify(customer).setPassword(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CustomerService#validateUser(Login)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testValidateUser3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.bankapp.bankapp.service.CustomerService.validateUser(CustomerService.java:34)
        //   See https://diff.blue/R013 to resolve this issue.

        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        Login l = new Login();
        l.setOtp(1L);
        l.setPassword("iloveyou");
        l.setUserID(1L);
        l.setUsername(1L);
        customerService.validateUser(l);
    }

    /**
     * Method under test: {@link CustomerService#validateUser(Login)}
     */
    @Test
    void testValidateUser4() {
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(null);

        Login l = new Login();
        l.setOtp(1L);
        l.setPassword("iloveyou");
        l.setUserID(1L);
        l.setUsername(1L);
        assertEquals("Invalid User", customerService.validateUser(l));
        verify(customerRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CustomerService#getCustomer(Long)}
     */
    @Test
    void testGetCustomer() {
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
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(customer, customerService.getCustomer(1L));
        verify(customerRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CustomerService#getCustomer(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCustomer2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.bankapp.bankapp.service.CustomerService.getCustomer(CustomerService.java:46)
        //   See https://diff.blue/R013 to resolve this issue.

        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        customerService.getCustomer(1L);
    }

    /**
     * Method under test: {@link CustomerService#changePassword(Login)}
     */
    @Test
    void testChangePassword() {
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
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.changePassword(Mockito.<String>any(), Mockito.<Long>any())).thenReturn(1);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Login l = new Login();
        l.setOtp(1L);
        l.setPassword("iloveyou");
        l.setUserID(1L);
        l.setUsername(1L);
        assertEquals("Password updated successfully", customerService.changePassword(l));
        verify(customerRepository).changePassword(Mockito.<String>any(), Mockito.<Long>any());
        verify(customerRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CustomerService#changePassword(Login)}
     */
    @Test
    void testChangePassword2() {
        Customer customer = mock(Customer.class);
        when(customer.getPassword()).thenReturn("foo");
        doNothing().when(customer).setAddress(Mockito.<String>any());
        doNothing().when(customer).setCustomer_Id(Mockito.<Long>any());
        doNothing().when(customer).setDob(Mockito.<String>any());
        doNothing().when(customer).setEmail(Mockito.<String>any());
        doNothing().when(customer).setFathername(Mockito.<String>any());
        doNothing().when(customer).setMothername(Mockito.<String>any());
        doNothing().when(customer).setName(Mockito.<String>any());
        doNothing().when(customer).setPan_number(Mockito.<String>any());
        doNothing().when(customer).setPassword(Mockito.<String>any());
        customer.setAddress("42 Main St");
        customer.setCustomer_Id(1L);
        customer.setDob("Dob");
        customer.setEmail("jane.doe@example.org");
        customer.setFathername("Fathername");
        customer.setMothername("Mothername");
        customer.setName("Name");
        customer.setPan_number("42");
        customer.setPassword("iloveyou");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.changePassword(Mockito.<String>any(), Mockito.<Long>any())).thenReturn(1);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Login l = new Login();
        l.setOtp(1L);
        l.setPassword("iloveyou");
        l.setUserID(1L);
        l.setUsername(1L);
        assertEquals("Unsuccessful Password Update", customerService.changePassword(l));
        verify(customerRepository).changePassword(Mockito.<String>any(), Mockito.<Long>any());
        verify(customerRepository).findById(Mockito.<Long>any());
        verify(customer).getPassword();
        verify(customer).setAddress(Mockito.<String>any());
        verify(customer).setCustomer_Id(Mockito.<Long>any());
        verify(customer).setDob(Mockito.<String>any());
        verify(customer).setEmail(Mockito.<String>any());
        verify(customer).setFathername(Mockito.<String>any());
        verify(customer).setMothername(Mockito.<String>any());
        verify(customer).setName(Mockito.<String>any());
        verify(customer).setPan_number(Mockito.<String>any());
        verify(customer).setPassword(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CustomerService#changePassword(Login)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testChangePassword3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.bankapp.bankapp.service.CustomerService.changePassword(CustomerService.java:53)
        //   See https://diff.blue/R013 to resolve this issue.

        when(customerRepository.changePassword(Mockito.<String>any(), Mockito.<Long>any())).thenReturn(1);
        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        Login l = new Login();
        l.setOtp(1L);
        l.setPassword("iloveyou");
        l.setUserID(1L);
        l.setUsername(1L);
        customerService.changePassword(l);
    }

    /**
     * Method under test: {@link CustomerService#forgotPassword(Login)}
     */
    @Test
    void testForgotPassword() {
        Login l = new Login();
        l.setOtp(1L);
        l.setPassword("iloveyou");
        l.setUserID(1L);
        l.setUsername(1L);
        assertEquals("OTP entered is wrong", customerService.forgotPassword(l));
    }

    /**
     * Method under test: {@link CustomerService#forgotPassword(Login)}
     */
    @Test
    void testForgotPassword2() {
        Login l = mock(Login.class);
        when(l.getOtp()).thenReturn(1L);
        doNothing().when(l).setOtp(Mockito.<Long>any());
        doNothing().when(l).setPassword(Mockito.<String>any());
        doNothing().when(l).setUserID(Mockito.<Long>any());
        doNothing().when(l).setUsername(Mockito.<Long>any());
        l.setOtp(1L);
        l.setPassword("iloveyou");
        l.setUserID(1L);
        l.setUsername(1L);
        assertEquals("OTP entered is wrong", customerService.forgotPassword(l));
        verify(l).getOtp();
        verify(l).setOtp(Mockito.<Long>any());
        verify(l).setPassword(Mockito.<String>any());
        verify(l).setUserID(Mockito.<Long>any());
        verify(l).setUsername(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CustomerService#forgotPassword(Login)}
     */
    @Test
    void testForgotPassword3() {
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
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.changePassword(Mockito.<String>any(), Mockito.<Long>any())).thenReturn(1);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Login l = mock(Login.class);
        when(l.getUserID()).thenReturn(1L);
        when(l.getPassword()).thenReturn("iloveyou");
        when(l.getOtp()).thenReturn(1234L);
        doNothing().when(l).setOtp(Mockito.<Long>any());
        doNothing().when(l).setPassword(Mockito.<String>any());
        doNothing().when(l).setUserID(Mockito.<Long>any());
        doNothing().when(l).setUsername(Mockito.<Long>any());
        l.setOtp(1L);
        l.setPassword("iloveyou");
        l.setUserID(1L);
        l.setUsername(1L);
        assertEquals("Password updated successfully", customerService.forgotPassword(l));
        verify(customerRepository).changePassword(Mockito.<String>any(), Mockito.<Long>any());
        verify(customerRepository).findById(Mockito.<Long>any());
        verify(l).getOtp();
        verify(l, atLeast(1)).getUserID();
        verify(l).getPassword();
        verify(l).setOtp(Mockito.<Long>any());
        verify(l).setPassword(Mockito.<String>any());
        verify(l).setUserID(Mockito.<Long>any());
        verify(l).setUsername(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CustomerService#forgotPassword(Login)}
     */
    @Test
    void testForgotPassword4() {
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
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.changePassword(Mockito.<String>any(), Mockito.<Long>any())).thenReturn(0);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Login l = mock(Login.class);
        when(l.getUserID()).thenReturn(1L);
        when(l.getPassword()).thenReturn("iloveyou");
        when(l.getOtp()).thenReturn(1234L);
        doNothing().when(l).setOtp(Mockito.<Long>any());
        doNothing().when(l).setPassword(Mockito.<String>any());
        doNothing().when(l).setUserID(Mockito.<Long>any());
        doNothing().when(l).setUsername(Mockito.<Long>any());
        l.setOtp(1L);
        l.setPassword("iloveyou");
        l.setUserID(1L);
        l.setUsername(1L);
        assertEquals("Unsuccessful Password Update", customerService.forgotPassword(l));
        verify(customerRepository).changePassword(Mockito.<String>any(), Mockito.<Long>any());
        verify(customerRepository).findById(Mockito.<Long>any());
        verify(l).getOtp();
        verify(l, atLeast(1)).getUserID();
        verify(l).getPassword();
        verify(l).setOtp(Mockito.<Long>any());
        verify(l).setPassword(Mockito.<String>any());
        verify(l).setUserID(Mockito.<Long>any());
        verify(l).setUsername(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CustomerService#forgotPassword(Login)}
     */
    @Test
    void testForgotPassword5() {
        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        Login l = mock(Login.class);
        when(l.getUserID()).thenReturn(1L);
        when(l.getOtp()).thenReturn(1234L);
        doNothing().when(l).setOtp(Mockito.<Long>any());
        doNothing().when(l).setPassword(Mockito.<String>any());
        doNothing().when(l).setUserID(Mockito.<Long>any());
        doNothing().when(l).setUsername(Mockito.<Long>any());
        l.setOtp(1L);
        l.setPassword("iloveyou");
        l.setUserID(1L);
        l.setUsername(1L);
        assertEquals("Invalid Credentials", customerService.forgotPassword(l));
        verify(customerRepository).findById(Mockito.<Long>any());
        verify(l).getOtp();
        verify(l).getUserID();
        verify(l).setOtp(Mockito.<Long>any());
        verify(l).setPassword(Mockito.<String>any());
        verify(l).setUserID(Mockito.<Long>any());
        verify(l).setUsername(Mockito.<Long>any());
    }
}

