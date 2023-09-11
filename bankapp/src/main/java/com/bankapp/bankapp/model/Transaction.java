package com.bankapp.bankapp.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long transaction_id;
	
	@ManyToOne
	@JoinColumn(name="sender_account_id")
	private Account sender_account;
	
	@ManyToOne
	@JoinColumn(name="receiver_account_id")
	private Account receiver_account;
	
	private double amount;
	
	private String status;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate;

	public Transaction(Long transaction_id, Account sender_account, Account receiver_account, double amount,
			String status, Date transactionDate) {
		super();
		this.transaction_id = transaction_id;
		this.sender_account = sender_account;
		this.receiver_account = receiver_account;
		this.amount = amount;
		this.status = status;
		this.transactionDate = transactionDate;
	}

	public Long getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(Long transaction_id) {
		this.transaction_id = transaction_id;
	}

	public Account getSender_account_id() {
		return sender_account;
	}

	public void setSender_account_id(Account sender_account) {
		this.sender_account = sender_account;
	}

	public Account getReceiver_account_id() {
		return receiver_account;
	}

	public void setReceiver_account_id(Account receiver_account) {
		this.receiver_account = receiver_account;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	
}
