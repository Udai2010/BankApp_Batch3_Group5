package com.bankapp.bankapp.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="account")
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long account_id;
	private double balance;
	private String account_type;
	private String IFSC;
	private String branch;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	@OneToMany(mappedBy="sender_account",cascade=CascadeType.ALL)
	private List<Transaction> sent_transactions = new ArrayList<>();
	
	@OneToMany(mappedBy="receiver_account",cascade=CascadeType.ALL)
	private List<Transaction> received_transactions = new ArrayList<>();

	public Account(Long account_id, double balance, String account_type, String iFSC, String branch, Customer customer,
			List<Transaction> sent_transactions, List<Transaction> received_transactions) {
		super();
		this.account_id = account_id;
		this.balance = balance;
		this.account_type = account_type;
		IFSC = iFSC;
		this.branch = branch;
		this.customer = customer;
		this.sent_transactions = sent_transactions;
		this.received_transactions = received_transactions;
	}

	public Long getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public String getIFSC() {
		return IFSC;
	}

	public void setIFSC(String iFSC) {
		IFSC = iFSC;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Transaction> getSent_transactions() {
		return sent_transactions;
	}

	public void setSent_transactions(List<Transaction> sent_transactions) {
		this.sent_transactions = sent_transactions;
	}

	public List<Transaction> getReceived_transactions() {
		return received_transactions;
	}

	public void setReceived_transactions(List<Transaction> received_transactions) {
		this.received_transactions = received_transactions;
	}

		
}
