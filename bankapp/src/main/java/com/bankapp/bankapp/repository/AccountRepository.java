package com.bankapp.bankapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bankapp.bankapp.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    @Query("SELECT a FROM Account a WHERE a.customer.customer_Id=?1")
	public List<Account> findAllAccounts(Long customerId);

    @Query("Select a.balance from Account a where a.account_id=?1")
    public double getBalance(Long accountno);
    
    @Query("Select a.status from Account a where a.account_id=?1")
    public String getStatus(Long accountno);

    @Modifying
	@Query("Update Account a set a.balance=a.balance-?1 where a.account_id=?2" )
	public int withdraw(double amount, Long accountno);
	
	@Modifying
	@Query("Update Account a set a.balance=a.balance+?1 where a.account_id=?2" )
	public int deposit(double amount,Long accountno);
	
	@Modifying
	@Query("Update Account a set a.status=?1 where a.account_id=?2" )
	public void Access(String s, Long accountno);
}

