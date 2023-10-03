package com.bankapp.bankapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bankapp.bankapp.model.Account;
import com.bankapp.bankapp.model.Admin;
import com.bankapp.bankapp.model.AdminLogin;
import com.bankapp.bankapp.model.Customer;
import com.bankapp.bankapp.repository.AccountRepository;
import com.bankapp.bankapp.repository.AdminRepository;
import com.bankapp.bankapp.repository.CustomerRepository;
import com.bankapp.bankapp.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AdminService.class})
@ExtendWith(SpringExtension.class)
class AdminServiceTest {
    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private AdminRepository adminRepository;

    @Autowired
    private AdminService adminService;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private TransactionRepository transactionRepository;

    @Test
    void testCreateAdmin() {
        Admin admin = new Admin();
        admin.setAdmin_id(1L);
        admin.setDob("Dob");
        admin.setEmail("jane.doe@example.org");
        admin.setName("Name");
        admin.setPan_number("42");
        admin.setPassword("iloveyou");
        when(adminRepository.save(Mockito.<Admin>any())).thenReturn(admin);

        Admin a = new Admin();
        a.setAdmin_id(1L);
        a.setDob("Dob");
        a.setEmail("jane.doe@example.org");
        a.setName("Name");
        a.setPan_number("42");
        a.setPassword("iloveyou");
        assertSame(admin, adminService.createAdmin(a));
        verify(adminRepository).save(Mockito.<Admin>any());
    }

    @Test
    void testValidateAdmin() {
        Admin admin = new Admin();
        admin.setAdmin_id(1L);
        admin.setDob("Dob");
        admin.setEmail("jane.doe@example.org");
        admin.setName("Name");
        admin.setPan_number("42");
        admin.setPassword("iloveyou");
        Optional<Admin> ofResult = Optional.of(admin);
        when(adminRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        AdminLogin l = new AdminLogin();
        l.setAdmin_id(1L);
        l.setPassword("iloveyou");
        assertEquals("Valid Credentials", adminService.validateAdmin(l));
        verify(adminRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testValidateAdmin2() {
        Admin admin = mock(Admin.class);
        when(admin.getPassword()).thenReturn("foo");
        doNothing().when(admin).setAdmin_id(Mockito.<Long>any());
        doNothing().when(admin).setDob(Mockito.<String>any());
        doNothing().when(admin).setEmail(Mockito.<String>any());
        doNothing().when(admin).setName(Mockito.<String>any());
        doNothing().when(admin).setPan_number(Mockito.<String>any());
        doNothing().when(admin).setPassword(Mockito.<String>any());
        admin.setAdmin_id(1L);
        admin.setDob("Dob");
        admin.setEmail("jane.doe@example.org");
        admin.setName("Name");
        admin.setPan_number("42");
        admin.setPassword("iloveyou");
        Optional<Admin> ofResult = Optional.of(admin);
        when(adminRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        AdminLogin l = new AdminLogin();
        l.setAdmin_id(1L);
        l.setPassword("iloveyou");
        assertEquals("Invalid Credentials", adminService.validateAdmin(l));
        verify(adminRepository).findById(Mockito.<Long>any());
        verify(admin).getPassword();
        verify(admin).setAdmin_id(Mockito.<Long>any());
        verify(admin).setDob(Mockito.<String>any());
        verify(admin).setEmail(Mockito.<String>any());
        verify(admin).setName(Mockito.<String>any());
        verify(admin).setPan_number(Mockito.<String>any());
        verify(admin).setPassword(Mockito.<String>any());
    }

    @Test
    void testValidateAdmin3() {
        Optional<Admin> emptyResult = Optional.empty();
        when(adminRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        AdminLogin l = new AdminLogin();
        l.setAdmin_id(1L);
        l.setPassword("iloveyou");
        assertEquals("Invalid Credentials", adminService.validateAdmin(l));
        verify(adminRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testGetTransactions() {
        when(accountRepository.findAllAccounts(Mockito.<Long>any())).thenReturn(new ArrayList<>());

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
        assertTrue(adminService.getTransactions(1L).isEmpty());
        verify(accountRepository).findAllAccounts(Mockito.<Long>any());
        verify(customerRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testGetTransactions2() {
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

        ArrayList<Account> accountList = new ArrayList<>();
        accountList.add(account);
        when(accountRepository.findAllAccounts(Mockito.<Long>any())).thenReturn(accountList);

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
        Optional<Customer> ofResult = Optional.of(customer2);
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(transactionRepository.getTransactions(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        assertTrue(adminService.getTransactions(1L).isEmpty());
        verify(accountRepository).findAllAccounts(Mockito.<Long>any());
        verify(customerRepository).findById(Mockito.<Long>any());
        verify(transactionRepository).getTransactions(Mockito.<Long>any());
    }

    @Test
    void testGetTransactions3() {
        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertTrue(adminService.getTransactions(1L).isEmpty());
        verify(customerRepository).findById(Mockito.<Long>any());
    }
}

