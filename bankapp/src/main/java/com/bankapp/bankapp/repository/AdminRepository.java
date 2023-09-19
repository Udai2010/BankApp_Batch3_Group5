package com.bankapp.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.bankapp.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {


}
