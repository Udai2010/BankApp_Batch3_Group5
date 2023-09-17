package com.bankapp.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.bankapp.bankapp.model.Transaction;
import com.bankapp.bankapp.repository.AccountRepository;
import com.bankapp.bankapp.repository.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepo;
    @Autowired
    AccountRepository accountRepo;

    public Transaction createNewTransaction(Transaction t) {
		Transaction obj = transactionRepo.save(t);
		return obj;
		
	}

    public List<Transaction> getTransactionForAnAccount(Long accountId){
        return transactionRepo.getTransactions(accountId);
    }
}
