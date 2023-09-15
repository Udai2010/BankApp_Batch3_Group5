package com.bankapp.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankapp.bankapp.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
   
}
