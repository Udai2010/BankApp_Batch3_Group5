package com.bankapp.bankapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bankapp.bankapp.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    //@Query("SELECT a.account_id FROM Account a WHERE a.customer_id=?1")
	//public List<Account> getAllAccount(Long customerId);

    @Query("Select a.balance from Account a where a.account_id=?1")
    public double getBalance(Long accountno);

    @Modifying
	@Query("Update Account a set a.balance=a.balance-?1 where a.account_id=?2" )
	public int withdraw(double amount, Long accountno);
	
	@Modifying
	@Query("Update Account a set a.balance=a.balance+?1 where a.account_id=?2" )
	public int deposit(double amount,Long accountno);
}

