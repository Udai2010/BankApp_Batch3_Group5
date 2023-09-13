package com.bankapp.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.bankapp.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

}