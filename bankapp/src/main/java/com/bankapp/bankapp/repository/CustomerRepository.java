package com.bankapp.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.bankapp.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{
	
	Customer findByUsername(String usename);
}
