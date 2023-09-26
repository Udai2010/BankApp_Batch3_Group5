package com.bankapp.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import com.bankapp.bankapp.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
   
    @Query("Select t from Transaction t where t.receiver_account.account_id=?1 or t.sender_account.account_id=?1")
    public List<Transaction> getTransactions(Long accountId);
    
    @Query("Select t from Transaction t where (t.receiver_account.account_id=?1 or t.sender_account.account_id=?1) and t.transactionDate between ?2 and ?3")
    public List<Transaction> getStatement(Long accountId,Date sDate,Date eDate);

}
