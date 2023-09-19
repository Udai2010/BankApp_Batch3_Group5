package com.bankapp.bankapp.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.bankapp.bankapp.model.Account;
import com.bankapp.bankapp.model.AdminLogin;
import com.bankapp.bankapp.repository.AccountRepository;
import com.bankapp.bankapp.repository.AdminRepository;
import com.bankapp.bankapp.repository.TransactionRepository;
import com.bankapp.bankapp.model.Transaction;
import com.bankapp.bankapp.model.Admin;


@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepo;
    @Autowired
    AccountRepository accountRepo;
    @Autowired 
    TransactionRepository transactionRepo;

    public Admin createAdmin(Admin a) {
		Admin obj = adminRepo.save(a);
		return obj;
		
	}

	public String validateAdmin(AdminLogin l) {
		String result="Invalid Credentials";
		
		Optional<Admin> obj = adminRepo.findById(l.getAdmin_id());
		if(obj.isPresent())
			{
				 if(obj.get().getPassword().equals(l.getPassword()))
				{
					result="Valid Credentials";
				}
			}
		return result;
	}

    public List<Transaction> getTransactions(Long customer_id){
        List<Transaction> t=new ArrayList<Transaction>();
        List<Account> accounts = accountRepo.findAllAccounts(customer_id);

        for(int i = 0; i < accounts.size(); i++){
            t.addAll(transactionRepo.getTransactions(accounts.get(i).getAccount_id()));
        }
        return t;
    }

    
}
