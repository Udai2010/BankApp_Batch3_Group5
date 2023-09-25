package com.bankapp.bankapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bankapp.bankapp.model.Account;
import com.bankapp.bankapp.model.Customer;
import com.bankapp.bankapp.model.FundTransfer;
import com.bankapp.bankapp.model.Transaction;
import com.bankapp.bankapp.repository.AccountRepository;
import com.bankapp.bankapp.repository.CustomerRepository;
import com.bankapp.bankapp.repository.TransactionRepository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {AccountService.class})
@ExtendWith(SpringExtension.class)
class AccountServiceTest {
    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private TransactionRepository transactionRepository;

    /**
     * Method under test: {@link AccountService#createAccount(Account, Long)}
     */
    @Test
    void testCreateAccount() {
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
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account);

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

        Customer customer3 = new Customer();
        customer3.setAddress("42 Main St");
        customer3.setCustomer_Id(1L);
        customer3.setDob("Dob");
        customer3.setEmail("jane.doe@example.org");
        customer3.setFathername("Fathername");
        customer3.setMothername("Mothername");
        customer3.setName("Name");
        customer3.setPan_number("42");
        customer3.setPassword("iloveyou");

        Account account2 = new Account();
        account2.setAccount_id(1L);
        account2.setAccount_type("3");
        account2.setAnnual_income(10.0d);
        account2.setBalance(10.0d);
        account2.setBranch("janedoe/featurebranch");
        account2.setCustomer(customer3);
        account2.setDebit_card("Debit card");
        account2.setIFSC("I FSC");
        account2.setIncome_source("Income source");
        account2.setNet_banking("Net banking");
        account2.setOccupation_type("Occupation type");
        account2.setStatus("Status");
        assertSame(account, accountService.createAccount(account2, 1L));
        verify(accountRepository).save(Mockito.<Account>any());
        verify(customerRepository).findById(Mockito.<Long>any());
        assertSame(customer2, account2.getCustomer());
    }

    /**
     * Method under test: {@link AccountService#createAccount(Account, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateAccount2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.bankapp.bankapp.service.AccountService.createAccount(AccountService.java:30)
        //   See https://diff.blue/R013 to resolve this issue.

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
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account);
        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

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
        accountService.createAccount(account2, 1L);
    }

    /**
     * Method under test: {@link AccountService#getBalance(Long)}
     */
    @Test
    void testGetBalance() {
        when(accountRepository.getBalance(Mockito.<Long>any())).thenReturn(10.0d);
        assertEquals(10.0d, accountService.getBalance(1L));
        verify(accountRepository).getBalance(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#Access(String, Long)}
     */
    @Test
    void testAccess() {
        doNothing().when(accountRepository).Access(Mockito.<String>any(), Mockito.<Long>any());
        accountService.Access("foo", 1L);
        verify(accountRepository).Access(Mockito.<String>any(), Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#getStatus(Long)}
     */
    @Test
    void testGetStatus() {
        when(accountRepository.getStatus(Mockito.<Long>any())).thenReturn("Status");
        assertEquals("Status", accountService.getStatus(1L));
        verify(accountRepository).getStatus(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#withdrawAmount(double, Long, boolean)}
     */
    @Test
    void testWithdrawAmount() {
        when(accountRepository.withdraw(anyDouble(), Mockito.<Long>any())).thenReturn(1);
        when(accountRepository.getBalance(Mockito.<Long>any())).thenReturn(10.0d);
        assertEquals("Withdraw successful", accountService.withdrawAmount(10.0d, 1L, true));
        verify(accountRepository).getBalance(Mockito.<Long>any());
        verify(accountRepository).withdraw(anyDouble(), Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#withdrawAmount(double, Long, boolean)}
     */
    @Test
    void testWithdrawAmount2() {
        when(accountRepository.getBalance(Mockito.<Long>any())).thenReturn(0.5d);
        assertEquals("Insufficient funds", accountService.withdrawAmount(10.0d, 1L, true));
        verify(accountRepository).getBalance(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#withdrawAmount(double, Long, boolean)}
     */
    @Test
    void testWithdrawAmount3() {
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
        when(accountRepository.getReferenceById(Mockito.<Long>any())).thenReturn(account);
        when(accountRepository.withdraw(anyDouble(), Mockito.<Long>any())).thenReturn(1);
        when(accountRepository.getBalance(Mockito.<Long>any())).thenReturn(10.0d);

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

        Account receiver_account = new Account();
        receiver_account.setAccount_id(1L);
        receiver_account.setAccount_type("3");
        receiver_account.setAnnual_income(10.0d);
        receiver_account.setBalance(10.0d);
        receiver_account.setBranch("janedoe/featurebranch");
        receiver_account.setCustomer(customer2);
        receiver_account.setDebit_card("Debit card");
        receiver_account.setIFSC("I FSC");
        receiver_account.setIncome_source("Income source");
        receiver_account.setNet_banking("Net banking");
        receiver_account.setOccupation_type("Occupation type");
        receiver_account.setStatus("Status");

        Customer customer3 = new Customer();
        customer3.setAddress("42 Main St");
        customer3.setCustomer_Id(1L);
        customer3.setDob("Dob");
        customer3.setEmail("jane.doe@example.org");
        customer3.setFathername("Fathername");
        customer3.setMothername("Mothername");
        customer3.setName("Name");
        customer3.setPan_number("42");
        customer3.setPassword("iloveyou");

        Account receiver_account2 = new Account();
        receiver_account2.setAccount_id(1L);
        receiver_account2.setAccount_type("3");
        receiver_account2.setAnnual_income(10.0d);
        receiver_account2.setBalance(10.0d);
        receiver_account2.setBranch("janedoe/featurebranch");
        receiver_account2.setCustomer(customer3);
        receiver_account2.setDebit_card("Debit card");
        receiver_account2.setIFSC("I FSC");
        receiver_account2.setIncome_source("Income source");
        receiver_account2.setNet_banking("Net banking");
        receiver_account2.setOccupation_type("Occupation type");
        receiver_account2.setStatus("Status");

        Customer customer4 = new Customer();
        customer4.setAddress("42 Main St");
        customer4.setCustomer_Id(1L);
        customer4.setDob("Dob");
        customer4.setEmail("jane.doe@example.org");
        customer4.setFathername("Fathername");
        customer4.setMothername("Mothername");
        customer4.setName("Name");
        customer4.setPan_number("42");
        customer4.setPassword("iloveyou");

        Account sender_account = new Account();
        sender_account.setAccount_id(1L);
        sender_account.setAccount_type("3");
        sender_account.setAnnual_income(10.0d);
        sender_account.setBalance(10.0d);
        sender_account.setBranch("janedoe/featurebranch");
        sender_account.setCustomer(customer4);
        sender_account.setDebit_card("Debit card");
        sender_account.setIFSC("I FSC");
        sender_account.setIncome_source("Income source");
        sender_account.setNet_banking("Net banking");
        sender_account.setOccupation_type("Occupation type");
        sender_account.setStatus("Status");

        Customer customer5 = new Customer();
        customer5.setAddress("42 Main St");
        customer5.setCustomer_Id(1L);
        customer5.setDob("Dob");
        customer5.setEmail("jane.doe@example.org");
        customer5.setFathername("Fathername");
        customer5.setMothername("Mothername");
        customer5.setName("Name");
        customer5.setPan_number("42");
        customer5.setPassword("iloveyou");

        Account sender_account2 = new Account();
        sender_account2.setAccount_id(1L);
        sender_account2.setAccount_type("3");
        sender_account2.setAnnual_income(10.0d);
        sender_account2.setBalance(10.0d);
        sender_account2.setBranch("janedoe/featurebranch");
        sender_account2.setCustomer(customer5);
        sender_account2.setDebit_card("Debit card");
        sender_account2.setIFSC("I FSC");
        sender_account2.setIncome_source("Income source");
        sender_account2.setNet_banking("Net banking");
        sender_account2.setOccupation_type("Occupation type");
        sender_account2.setStatus("Status");

        Transaction transaction = new Transaction();
        transaction.setAmount(10.0d);
        transaction.setReceiver_account(receiver_account);
        transaction.setReceiver_account_id(receiver_account2);
        transaction.setSender_account(sender_account);
        transaction.setSender_account_id(sender_account2);
        transaction.setStatus("Status");
        transaction
                .setTransactionDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        transaction.setTransactionType("Transaction Type");
        transaction.setTransaction_id(1L);
        when(transactionRepository.save(Mockito.<Transaction>any())).thenReturn(transaction);
        assertEquals("Withdraw successful", accountService.withdrawAmount(10.0d, 1L, false));
        verify(accountRepository).getBalance(Mockito.<Long>any());
        verify(accountRepository).withdraw(anyDouble(), Mockito.<Long>any());
        verify(accountRepository).getReferenceById(Mockito.<Long>any());
        verify(transactionRepository).save(Mockito.<Transaction>any());
    }

    /**
     * Method under test: {@link AccountService#depositAmount(double, Long, boolean)}
     */
    @Test
    void testDepositAmount() {
        when(accountRepository.deposit(anyDouble(), Mockito.<Long>any())).thenReturn(1);
        assertEquals("Deposit successful", accountService.depositAmount(10.0d, 1L, true));
        verify(accountRepository).deposit(anyDouble(), Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#depositAmount(double, Long, boolean)}
     */
    @Test
    void testDepositAmount2() {
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
        when(accountRepository.getReferenceById(Mockito.<Long>any())).thenReturn(account);
        when(accountRepository.deposit(anyDouble(), Mockito.<Long>any())).thenReturn(1);

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

        Account receiver_account = new Account();
        receiver_account.setAccount_id(1L);
        receiver_account.setAccount_type("3");
        receiver_account.setAnnual_income(10.0d);
        receiver_account.setBalance(10.0d);
        receiver_account.setBranch("janedoe/featurebranch");
        receiver_account.setCustomer(customer2);
        receiver_account.setDebit_card("Debit card");
        receiver_account.setIFSC("I FSC");
        receiver_account.setIncome_source("Income source");
        receiver_account.setNet_banking("Net banking");
        receiver_account.setOccupation_type("Occupation type");
        receiver_account.setStatus("Status");

        Customer customer3 = new Customer();
        customer3.setAddress("42 Main St");
        customer3.setCustomer_Id(1L);
        customer3.setDob("Dob");
        customer3.setEmail("jane.doe@example.org");
        customer3.setFathername("Fathername");
        customer3.setMothername("Mothername");
        customer3.setName("Name");
        customer3.setPan_number("42");
        customer3.setPassword("iloveyou");

        Account receiver_account2 = new Account();
        receiver_account2.setAccount_id(1L);
        receiver_account2.setAccount_type("3");
        receiver_account2.setAnnual_income(10.0d);
        receiver_account2.setBalance(10.0d);
        receiver_account2.setBranch("janedoe/featurebranch");
        receiver_account2.setCustomer(customer3);
        receiver_account2.setDebit_card("Debit card");
        receiver_account2.setIFSC("I FSC");
        receiver_account2.setIncome_source("Income source");
        receiver_account2.setNet_banking("Net banking");
        receiver_account2.setOccupation_type("Occupation type");
        receiver_account2.setStatus("Status");

        Customer customer4 = new Customer();
        customer4.setAddress("42 Main St");
        customer4.setCustomer_Id(1L);
        customer4.setDob("Dob");
        customer4.setEmail("jane.doe@example.org");
        customer4.setFathername("Fathername");
        customer4.setMothername("Mothername");
        customer4.setName("Name");
        customer4.setPan_number("42");
        customer4.setPassword("iloveyou");

        Account sender_account = new Account();
        sender_account.setAccount_id(1L);
        sender_account.setAccount_type("3");
        sender_account.setAnnual_income(10.0d);
        sender_account.setBalance(10.0d);
        sender_account.setBranch("janedoe/featurebranch");
        sender_account.setCustomer(customer4);
        sender_account.setDebit_card("Debit card");
        sender_account.setIFSC("I FSC");
        sender_account.setIncome_source("Income source");
        sender_account.setNet_banking("Net banking");
        sender_account.setOccupation_type("Occupation type");
        sender_account.setStatus("Status");

        Customer customer5 = new Customer();
        customer5.setAddress("42 Main St");
        customer5.setCustomer_Id(1L);
        customer5.setDob("Dob");
        customer5.setEmail("jane.doe@example.org");
        customer5.setFathername("Fathername");
        customer5.setMothername("Mothername");
        customer5.setName("Name");
        customer5.setPan_number("42");
        customer5.setPassword("iloveyou");

        Account sender_account2 = new Account();
        sender_account2.setAccount_id(1L);
        sender_account2.setAccount_type("3");
        sender_account2.setAnnual_income(10.0d);
        sender_account2.setBalance(10.0d);
        sender_account2.setBranch("janedoe/featurebranch");
        sender_account2.setCustomer(customer5);
        sender_account2.setDebit_card("Debit card");
        sender_account2.setIFSC("I FSC");
        sender_account2.setIncome_source("Income source");
        sender_account2.setNet_banking("Net banking");
        sender_account2.setOccupation_type("Occupation type");
        sender_account2.setStatus("Status");

        Transaction transaction = new Transaction();
        transaction.setAmount(10.0d);
        transaction.setReceiver_account(receiver_account);
        transaction.setReceiver_account_id(receiver_account2);
        transaction.setSender_account(sender_account);
        transaction.setSender_account_id(sender_account2);
        transaction.setStatus("Status");
        transaction
                .setTransactionDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        transaction.setTransactionType("Transaction Type");
        transaction.setTransaction_id(1L);
        when(transactionRepository.save(Mockito.<Transaction>any())).thenReturn(transaction);
        assertEquals("Deposit successful", accountService.depositAmount(10.0d, 1L, false));
        verify(accountRepository).deposit(anyDouble(), Mockito.<Long>any());
        verify(accountRepository).getReferenceById(Mockito.<Long>any());
        verify(transactionRepository).save(Mockito.<Transaction>any());
    }

    /**
     * Method under test: {@link AccountService#fundTransfer(FundTransfer)}
     */
    @Test
    void testFundTransfer() {
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
        when(accountRepository.deposit(anyDouble(), Mockito.<Long>any())).thenReturn(1);
        when(accountRepository.withdraw(anyDouble(), Mockito.<Long>any())).thenReturn(1);
        when(accountRepository.getReferenceById(Mockito.<Long>any())).thenReturn(account);
        when(accountRepository.getBalance(Mockito.<Long>any())).thenReturn(10.0d);

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

        Account receiver_account = new Account();
        receiver_account.setAccount_id(1L);
        receiver_account.setAccount_type("3");
        receiver_account.setAnnual_income(10.0d);
        receiver_account.setBalance(10.0d);
        receiver_account.setBranch("janedoe/featurebranch");
        receiver_account.setCustomer(customer2);
        receiver_account.setDebit_card("Debit card");
        receiver_account.setIFSC("I FSC");
        receiver_account.setIncome_source("Income source");
        receiver_account.setNet_banking("Net banking");
        receiver_account.setOccupation_type("Occupation type");
        receiver_account.setStatus("Status");

        Customer customer3 = new Customer();
        customer3.setAddress("42 Main St");
        customer3.setCustomer_Id(1L);
        customer3.setDob("Dob");
        customer3.setEmail("jane.doe@example.org");
        customer3.setFathername("Fathername");
        customer3.setMothername("Mothername");
        customer3.setName("Name");
        customer3.setPan_number("42");
        customer3.setPassword("iloveyou");

        Account receiver_account2 = new Account();
        receiver_account2.setAccount_id(1L);
        receiver_account2.setAccount_type("3");
        receiver_account2.setAnnual_income(10.0d);
        receiver_account2.setBalance(10.0d);
        receiver_account2.setBranch("janedoe/featurebranch");
        receiver_account2.setCustomer(customer3);
        receiver_account2.setDebit_card("Debit card");
        receiver_account2.setIFSC("I FSC");
        receiver_account2.setIncome_source("Income source");
        receiver_account2.setNet_banking("Net banking");
        receiver_account2.setOccupation_type("Occupation type");
        receiver_account2.setStatus("Status");

        Customer customer4 = new Customer();
        customer4.setAddress("42 Main St");
        customer4.setCustomer_Id(1L);
        customer4.setDob("Dob");
        customer4.setEmail("jane.doe@example.org");
        customer4.setFathername("Fathername");
        customer4.setMothername("Mothername");
        customer4.setName("Name");
        customer4.setPan_number("42");
        customer4.setPassword("iloveyou");

        Account sender_account = new Account();
        sender_account.setAccount_id(1L);
        sender_account.setAccount_type("3");
        sender_account.setAnnual_income(10.0d);
        sender_account.setBalance(10.0d);
        sender_account.setBranch("janedoe/featurebranch");
        sender_account.setCustomer(customer4);
        sender_account.setDebit_card("Debit card");
        sender_account.setIFSC("I FSC");
        sender_account.setIncome_source("Income source");
        sender_account.setNet_banking("Net banking");
        sender_account.setOccupation_type("Occupation type");
        sender_account.setStatus("Status");

        Customer customer5 = new Customer();
        customer5.setAddress("42 Main St");
        customer5.setCustomer_Id(1L);
        customer5.setDob("Dob");
        customer5.setEmail("jane.doe@example.org");
        customer5.setFathername("Fathername");
        customer5.setMothername("Mothername");
        customer5.setName("Name");
        customer5.setPan_number("42");
        customer5.setPassword("iloveyou");

        Account sender_account2 = new Account();
        sender_account2.setAccount_id(1L);
        sender_account2.setAccount_type("3");
        sender_account2.setAnnual_income(10.0d);
        sender_account2.setBalance(10.0d);
        sender_account2.setBranch("janedoe/featurebranch");
        sender_account2.setCustomer(customer5);
        sender_account2.setDebit_card("Debit card");
        sender_account2.setIFSC("I FSC");
        sender_account2.setIncome_source("Income source");
        sender_account2.setNet_banking("Net banking");
        sender_account2.setOccupation_type("Occupation type");
        sender_account2.setStatus("Status");

        Transaction transaction = new Transaction();
        transaction.setAmount(10.0d);
        transaction.setReceiver_account(receiver_account);
        transaction.setReceiver_account_id(receiver_account2);
        transaction.setSender_account(sender_account);
        transaction.setSender_account_id(sender_account2);
        transaction.setStatus("Status");
        transaction
                .setTransactionDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        transaction.setTransactionType("Transaction Type");
        transaction.setTransaction_id(1L);
        when(transactionRepository.save(Mockito.<Transaction>any())).thenReturn(transaction);
        assertEquals("Fund transfer completed", accountService.fundTransfer(new FundTransfer(3L, 3L, 10.0d)));
        verify(accountRepository).getBalance(Mockito.<Long>any());
        verify(accountRepository).deposit(anyDouble(), Mockito.<Long>any());
        verify(accountRepository).withdraw(anyDouble(), Mockito.<Long>any());
        verify(accountRepository, atLeast(1)).getReferenceById(Mockito.<Long>any());
        verify(transactionRepository).save(Mockito.<Transaction>any());
    }

    /**
     * Method under test: {@link AccountService#fundTransfer(FundTransfer)}
     */
    @Test
    void testFundTransfer2() {
        when(accountRepository.getBalance(Mockito.<Long>any())).thenReturn(0.5d);
        assertEquals("Failed due to insuffcient funds in senders account",
                accountService.fundTransfer(new FundTransfer(3L, 3L, 10.0d)));
        verify(accountRepository).getBalance(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#getAllAccounts(Long)}
     */
    @Test
    void testGetAllAccounts() {
        ArrayList<Account> accountList = new ArrayList<>();
        when(accountRepository.findAllAccounts(Mockito.<Long>any())).thenReturn(accountList);
        List<Account> actualAllAccounts = accountService.getAllAccounts(1L);
        assertSame(accountList, actualAllAccounts);
        assertTrue(actualAllAccounts.isEmpty());
        verify(accountRepository).findAllAccounts(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#getAccountDetails(Long)}
     */
    @Test
    void testGetAccountDetails() {
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
        when(accountRepository.getReferenceById(Mockito.<Long>any())).thenReturn(account);
        assertSame(account, accountService.getAccountDetails(1L));
        verify(accountRepository).getReferenceById(Mockito.<Long>any());
    }
}

