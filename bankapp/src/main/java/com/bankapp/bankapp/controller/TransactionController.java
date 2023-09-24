package com.bankapp.bankapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.bankapp.model.Transaction;
import com.bankapp.bankapp.service.TransactionService;

@RestController
@CrossOrigin("*")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping("transactions/{accountId}")
    public List<Transaction> geTransactions(@PathVariable("accountId")Long accountId){
        return transactionService.getTransactionForAnAccount(accountId);
    }
    
    @GetMapping("statement/{accountId}/{sdate}/{edate}")
    public List<Transaction> getStatement(@PathVariable("accountId")Long accountId,
    		@PathVariable("sdate") String sdate,
    		@PathVariable("edate") String edate){
        return transactionService.getStatement(accountId,sdate,edate);
    }
}
