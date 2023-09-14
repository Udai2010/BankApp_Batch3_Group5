package com.bankapp.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.bankapp.bankapp.model.Customer;
import com.bankapp.bankapp.model.Account;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{
	  
}
