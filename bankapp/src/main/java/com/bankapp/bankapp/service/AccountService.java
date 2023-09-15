package com.bankapp.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

import com.bankapp.bankapp.model.Account;
import com.bankapp.bankapp.model.Customer;
import com.bankapp.bankapp.model.FundTransfer;
import com.bankapp.bankapp.repository.AccountRepository;
import com.bankapp.bankapp.repository.CustomerRepository;
import com.bankapp.bankapp.repository.TransactionRepository;
import com.bankapp.bankapp.model.Transaction;

import jakarta.transaction.Transactional;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepo;
	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	TransactionRepository transactionRepo;
	
	public Account createAccount(Account account, Long userID) {
		Customer obj = customerRepo.findById(userID).get();
		account.setCustomer(obj);
		return accountRepo.save(account);
		
	}

	public double getBalance(Long account_id){
		double balance = accountRepo.getBalance(account_id);
		return balance;
	}
	@Transactional
	public String withdrawAmount(double amount, Long account_id, boolean isFundTransfer) {
		double balance = getBalance(account_id);
		if(amount > balance){
			return "Insufficient funds";
		}
		accountRepo.withdraw(amount, account_id);
		if(!isFundTransfer){
			Transaction t = new Transaction();
			t.setAmount(amount);
			t.setStatus("Success");
			t.setTransactionDate(new Date());
			t.setTransactionType("Withdraw");
			t.setSender_account(accountRepo.getReferenceById(account_id));
			transactionRepo.save(t);
		}
		return "Withdraw successful";
	}
	@Transactional
	public String depositAmount(double amount, Long account_id, boolean isFundTransfer) {
		accountRepo.deposit(amount, account_id);
		if(!isFundTransfer){
			Transaction t = new Transaction();
			t.setAmount(amount);
			t.setStatus("Success");
			t.setTransactionDate(new Date());
			t.setTransactionType("Deposit");
			t.setSender_account(accountRepo.getReferenceById(account_id));
			transactionRepo.save(t);
		}
		return "Deposit successful";
	}

	@Transactional
	public String fundTransfer(FundTransfer ft){
		withdrawAmount(ft.getAmount(), ft.getSourceAccount(), true);
		depositAmount(ft.getAmount(), ft.getDestAccount(), true);
		Transaction t = new Transaction();
		t.setAmount(ft.getAmount());
		t.setStatus("Success");
		t.setTransactionDate(new Date());
		t.setTransactionType("Fund Transfer");
		t.setSender_account(accountRepo.getReferenceById(ft.getSourceAccount()));
		t.setReceiver_account(accountRepo.getReferenceById(ft.getDestAccount()));
		transactionRepo.save(t);
		return "Fund transfer completed";
	}
	
}
