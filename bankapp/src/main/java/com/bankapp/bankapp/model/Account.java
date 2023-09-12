package com.bankapp.bankapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	private String occupation_type;
	private String income_source;
	private double annual_income;
	private String debit_card;
	private String net_banking;
	private String status;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	@JsonBackReference
	private Customer customer;

	/*@OneToMany(mappedBy="sender_account",cascade=CascadeType.ALL)
	private List<Transaction> sent_transactions = new ArrayList<>();
	
	@OneToMany(mappedBy="receiver_account",cascade=CascadeType.ALL)
	private List<Transaction> received_transactions = new ArrayList<>();*/

	public Account(Long account_id, double balance, String account_type, String iFSC, String branch, Customer customer) {
		super();
		this.account_id = account_id;
		this.balance = balance;
		this.account_type = account_type;
		this.IFSC = iFSC;
		this.branch = branch;
		this.customer = customer;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getOccupation_type() {
		return occupation_type;
	}

	public void setOccupation_type(String occupation_type) {
		this.occupation_type = occupation_type;
	}

	public String getIncome_source() {
		return income_source;
	}

	public void setIncome_source(String income_source) {
		this.income_source = income_source;
	}

	public double getAnnual_income() {
		return annual_income;
	}

	public void setAnnual_income(double annual_income) {
		this.annual_income = annual_income;
	}

	public String getDebit_card() {
		return debit_card;
	}

	public void setDebit_card(String debit_card) {
		this.debit_card = debit_card;
	}

	public String getNet_banking() {
		return net_banking;
	}

	public void setNet_banking(String net_banking) {
		this.net_banking = net_banking;
	}


		
}
