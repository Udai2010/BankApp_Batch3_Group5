package com.bankapp.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bankapp.bankapp.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{
	  
	@Modifying
	@Query("Update Customer c set c.password=?1 where c.customer_Id=?2" )
	public int changePassword(String password, Long customerID);
}
