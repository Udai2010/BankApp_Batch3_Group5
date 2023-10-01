package com.bankapp.bankapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="account")
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long account_id;
	private double balance;
	@NotBlank(message="Account type can not be blank.")
	private String account_type;
	@Length(min=5, max=10, message="IFSC must be between 5 to 10 characters.")
	private String IFSC;
	@NotBlank(message="Branch can not be blank.")
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


	public Account(Long account_id, double balance, String account_type, String iFSC, String branch, Customer customer,
			String occupation_type, String income_source, double annual_income, String debit_card, String net_banking, String status) {
		super();
		this.account_id = account_id;
		this.balance = balance;
		this.account_type = account_type;
		this.IFSC = iFSC;
		this.branch = branch;
		this.customer = customer;

		this.occupation_type = occupation_type;
		this.income_source = income_source;
		this.annual_income = annual_income;
		this.debit_card = debit_card;
		this.net_banking = net_banking;
		this.status = status;
	}

	public Account() {
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

		
}
