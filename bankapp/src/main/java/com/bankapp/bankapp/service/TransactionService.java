package com.bankapp.bankapp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    
    public List<Transaction> getStatement(Long accountId,String sdate,String edate){
    	
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyyy-MM-dd");
    	//java.util.Date sd;
    	//java.util.Date ed;
		
    	
        java.util.Date sDate= null;
		java.util.Date eDate = null;
		try {
			sDate = sdf.parse(sdate);
			eDate=sdf.parse(edate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("exception by parsing date");
			e.printStackTrace();
		}
		return transactionRepo.getStatement(accountId,sDate,eDate);
    }
}
