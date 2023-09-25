package com.bankapp.bankapp.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import com.bankapp.bankapp.model.Account;
import com.bankapp.bankapp.model.FundTransfer;
import com.bankapp.bankapp.model.Withdraw;
import com.bankapp.bankapp.service.AccountService;
import com.bankapp.bankapp.service.CustomerService;



@RestController
@CrossOrigin("*")
public class AccountController {
    @Autowired
    AccountService accountService;
    
    @Autowired
    CustomerService customerService;
    
	@PostMapping("/createAccount/{uid}")
	public List<String> createNewAccount(@RequestBody Account account,@PathVariable("uid") Long userID) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		javax.validation.Validator validator = factory.getValidator();
		Set<ConstraintViolation<Account>> violations = validator.validate(account);

		List<String> resp = new ArrayList<String>();
		if(violations.size() == 0){
			Account acc = accountService.createAccount(account, userID);
			System.out.print(acc.toString());
			if(acc!=null)
			{	resp.add("Account created succesfully");
				return resp;
			}
		}

		for(ConstraintViolation<Account> t: violations){
			resp.add(t.getMessage());
		}
		return resp;
	}
		
	
	@PutMapping("withdraw")
	public String withdrawAccount(@RequestBody Withdraw withdraw){
		return accountService.withdrawAmount(withdraw.getAmount(), withdraw.getAccount_id(), false);
	}

	@PutMapping("deposit")
	public String depositAccount(@RequestBody Withdraw deposit){
		return accountService.depositAmount(deposit.getAmount(), deposit.getAccount_id(), false);
	}

	@PutMapping("fundtransfer")
	public String fundTransfer(@RequestBody FundTransfer ft){
		return accountService.fundTransfer(ft);
	}
	
	@GetMapping("/balance/{accId}")
	public double Balance(@PathVariable("accId") Long AccId) {
		return accountService.getBalance(AccId);
	}

	@GetMapping("account/{id}")
	public List<Account> getAccount(@PathVariable("id") Long accountno){
		return accountService.getAllAccounts(accountno);
	}


}
