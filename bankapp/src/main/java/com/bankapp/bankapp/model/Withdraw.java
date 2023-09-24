package com.bankapp.bankapp.model;

public class Withdraw {
    private Long account_id;
    private double amount;

    public Withdraw(Long account_id, double amount) {
        this.account_id = account_id;
        this.amount = amount;
    }
    public Long getAccount_id() {
        return account_id;
    }
    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    
}
