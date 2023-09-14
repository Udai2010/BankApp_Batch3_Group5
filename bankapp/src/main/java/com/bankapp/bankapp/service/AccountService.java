package com.bankapp.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.bankapp.model.Account;
import com.bankapp.bankapp.model.Customer;
import com.bankapp.bankapp.model.FundTransfer;
import com.bankapp.bankapp.repository.AccountRepository;
import com.bankapp.bankapp.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepo;
	@Autowired
	CustomerRepository customerRepo;
	
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
	public String withdrawAmount(double amount, Long account_id) {
		double balance = getBalance(account_id);
		if(amount > balance){
			return "Insufficient funds";
		}
		accountRepo.withdraw(amount, account_id);
		return "Withdraw successful";
	}
	@Transactional
	public String depositAmount(double amount, Long account_id) {
		accountRepo.deposit(amount, account_id);
		return "Deposit successful";
	}

	@Transactional
	public String fundTransfer(FundTransfer ft){
		withdrawAmount(ft.getAmount(), ft.getSourceAccount());
		depositAmount(ft.getAmount(), ft.getDestAccount());

		return "Fund transfer completed";
	}
	
}
