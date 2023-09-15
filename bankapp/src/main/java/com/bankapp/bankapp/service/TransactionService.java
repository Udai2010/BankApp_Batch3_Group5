package com.bankapp.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapp.bankapp.model.Transaction;
import com.bankapp.bankapp.repository.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepo;

     public Transaction createNewTransaction(Transaction t) {
		Transaction obj = transactionRepo.save(t);
		return obj;
		
	}
}
