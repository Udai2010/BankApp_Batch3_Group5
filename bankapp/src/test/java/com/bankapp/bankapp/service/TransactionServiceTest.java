package com.bankapp.bankapp.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bankapp.bankapp.model.Account;
import com.bankapp.bankapp.model.Customer;
import com.bankapp.bankapp.model.Transaction;
import com.bankapp.bankapp.repository.AccountRepository;
import com.bankapp.bankapp.repository.TransactionRepository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TransactionService.class})
@ExtendWith(SpringExtension.class)
class TransactionServiceTest {
    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;

    @Test
    void testCreateNewTransaction() {
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

        Account receiver_account = new Account();
        receiver_account.setAccount_id(1L);
        receiver_account.setAccount_type("3");
        receiver_account.setAnnual_income(10.0d);
        receiver_account.setBalance(10.0d);
        receiver_account.setBranch("janedoe/featurebranch");
        receiver_account.setCustomer(customer);
        receiver_account.setDebit_card("Debit card");
        receiver_account.setIFSC("I FSC");
        receiver_account.setIncome_source("Income source");
        receiver_account.setNet_banking("Net banking");
        receiver_account.setOccupation_type("Occupation type");
        receiver_account.setStatus("Status");

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

        Account receiver_account2 = new Account();
        receiver_account2.setAccount_id(1L);
        receiver_account2.setAccount_type("3");
        receiver_account2.setAnnual_income(10.0d);
        receiver_account2.setBalance(10.0d);
        receiver_account2.setBranch("janedoe/featurebranch");
        receiver_account2.setCustomer(customer2);
        receiver_account2.setDebit_card("Debit card");
        receiver_account2.setIFSC("I FSC");
        receiver_account2.setIncome_source("Income source");
        receiver_account2.setNet_banking("Net banking");
        receiver_account2.setOccupation_type("Occupation type");
        receiver_account2.setStatus("Status");

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

        Account sender_account = new Account();
        sender_account.setAccount_id(1L);
        sender_account.setAccount_type("3");
        sender_account.setAnnual_income(10.0d);
        sender_account.setBalance(10.0d);
        sender_account.setBranch("janedoe/featurebranch");
        sender_account.setCustomer(customer3);
        sender_account.setDebit_card("Debit card");
        sender_account.setIFSC("I FSC");
        sender_account.setIncome_source("Income source");
        sender_account.setNet_banking("Net banking");
        sender_account.setOccupation_type("Occupation type");
        sender_account.setStatus("Status");

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

        Account sender_account2 = new Account();
        sender_account2.setAccount_id(1L);
        sender_account2.setAccount_type("3");
        sender_account2.setAnnual_income(10.0d);
        sender_account2.setBalance(10.0d);
        sender_account2.setBranch("janedoe/featurebranch");
        sender_account2.setCustomer(customer4);
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

        Account receiver_account3 = new Account();
        receiver_account3.setAccount_id(1L);
        receiver_account3.setAccount_type("3");
        receiver_account3.setAnnual_income(10.0d);
        receiver_account3.setBalance(10.0d);
        receiver_account3.setBranch("janedoe/featurebranch");
        receiver_account3.setCustomer(customer5);
        receiver_account3.setDebit_card("Debit card");
        receiver_account3.setIFSC("I FSC");
        receiver_account3.setIncome_source("Income source");
        receiver_account3.setNet_banking("Net banking");
        receiver_account3.setOccupation_type("Occupation type");
        receiver_account3.setStatus("Status");

        Customer customer6 = new Customer();
        customer6.setAddress("42 Main St");
        customer6.setCustomer_Id(1L);
        customer6.setDob("Dob");
        customer6.setEmail("jane.doe@example.org");
        customer6.setFathername("Fathername");
        customer6.setMothername("Mothername");
        customer6.setName("Name");
        customer6.setPan_number("42");
        customer6.setPassword("iloveyou");

        Account receiver_account4 = new Account();
        receiver_account4.setAccount_id(1L);
        receiver_account4.setAccount_type("3");
        receiver_account4.setAnnual_income(10.0d);
        receiver_account4.setBalance(10.0d);
        receiver_account4.setBranch("janedoe/featurebranch");
        receiver_account4.setCustomer(customer6);
        receiver_account4.setDebit_card("Debit card");
        receiver_account4.setIFSC("I FSC");
        receiver_account4.setIncome_source("Income source");
        receiver_account4.setNet_banking("Net banking");
        receiver_account4.setOccupation_type("Occupation type");
        receiver_account4.setStatus("Status");

        Customer customer7 = new Customer();
        customer7.setAddress("42 Main St");
        customer7.setCustomer_Id(1L);
        customer7.setDob("Dob");
        customer7.setEmail("jane.doe@example.org");
        customer7.setFathername("Fathername");
        customer7.setMothername("Mothername");
        customer7.setName("Name");
        customer7.setPan_number("42");
        customer7.setPassword("iloveyou");

        Account sender_account3 = new Account();
        sender_account3.setAccount_id(1L);
        sender_account3.setAccount_type("3");
        sender_account3.setAnnual_income(10.0d);
        sender_account3.setBalance(10.0d);
        sender_account3.setBranch("janedoe/featurebranch");
        sender_account3.setCustomer(customer7);
        sender_account3.setDebit_card("Debit card");
        sender_account3.setIFSC("I FSC");
        sender_account3.setIncome_source("Income source");
        sender_account3.setNet_banking("Net banking");
        sender_account3.setOccupation_type("Occupation type");
        sender_account3.setStatus("Status");

        Customer customer8 = new Customer();
        customer8.setAddress("42 Main St");
        customer8.setCustomer_Id(1L);
        customer8.setDob("Dob");
        customer8.setEmail("jane.doe@example.org");
        customer8.setFathername("Fathername");
        customer8.setMothername("Mothername");
        customer8.setName("Name");
        customer8.setPan_number("42");
        customer8.setPassword("iloveyou");

        Account sender_account4 = new Account();
        sender_account4.setAccount_id(1L);
        sender_account4.setAccount_type("3");
        sender_account4.setAnnual_income(10.0d);
        sender_account4.setBalance(10.0d);
        sender_account4.setBranch("janedoe/featurebranch");
        sender_account4.setCustomer(customer8);
        sender_account4.setDebit_card("Debit card");
        sender_account4.setIFSC("I FSC");
        sender_account4.setIncome_source("Income source");
        sender_account4.setNet_banking("Net banking");
        sender_account4.setOccupation_type("Occupation type");
        sender_account4.setStatus("Status");

        Transaction t = new Transaction();
        t.setAmount(10.0d);
        t.setReceiver_account(receiver_account3);
        t.setReceiver_account_id(receiver_account4);
        t.setSender_account(sender_account3);
        t.setSender_account_id(sender_account4);
        t.setStatus("Status");
        t.setTransactionDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        t.setTransactionType("Transaction Type");
        t.setTransaction_id(1L);
        assertSame(transaction, transactionService.createNewTransaction(t));
        verify(transactionRepository).save(Mockito.<Transaction>any());
    }

    @Test
    void testGetTransactionForAnAccount() {
        ArrayList<Transaction> transactionList = new ArrayList<>();
        when(transactionRepository.getTransactions(Mockito.<Long>any())).thenReturn(transactionList);
        List<Transaction> actualTransactionForAnAccount = transactionService.getTransactionForAnAccount(1L);
        assertSame(transactionList, actualTransactionForAnAccount);
        assertTrue(actualTransactionForAnAccount.isEmpty());
        verify(transactionRepository).getTransactions(Mockito.<Long>any());
    }

    @Test
    void testGetStatement() {
        ArrayList<Transaction> transactionList = new ArrayList<>();
        when(transactionRepository.getStatement(Mockito.<Long>any(), Mockito.<Date>any(), Mockito.<Date>any()))
                .thenReturn(transactionList);
        List<Transaction> actualStatement = transactionService.getStatement(1L, "2020-03-01", "2020-03-01");
        assertSame(transactionList, actualStatement);
        assertTrue(actualStatement.isEmpty());
        verify(transactionRepository).getStatement(Mockito.<Long>any(), Mockito.<Date>any(), Mockito.<Date>any());
    }

    @Test
    void testGetStatement2() {
        ArrayList<Transaction> transactionList = new ArrayList<>();
        when(transactionRepository.getStatement(Mockito.<Long>any(), Mockito.<Date>any(), Mockito.<Date>any()))
                .thenReturn(transactionList);
        List<Transaction> actualStatement = transactionService.getStatement(1L, "2020/03/01", "2020-03-01");
        assertSame(transactionList, actualStatement);
        assertTrue(actualStatement.isEmpty());
        verify(transactionRepository).getStatement(Mockito.<Long>any(), Mockito.<Date>any(), Mockito.<Date>any());
    }

    @Test
    void testGetStatement3() {
        ArrayList<Transaction> transactionList = new ArrayList<>();
        when(transactionRepository.getStatement(Mockito.<Long>any(), Mockito.<Date>any(), Mockito.<Date>any()))
                .thenReturn(transactionList);
        List<Transaction> actualStatement = transactionService.getStatement(1L, "2020-03-01", "2020/03/01");
        assertSame(transactionList, actualStatement);
        assertTrue(actualStatement.isEmpty());
        verify(transactionRepository).getStatement(Mockito.<Long>any(), Mockito.<Date>any(), Mockito.<Date>any());
    }
}

