package com.bankapp.bankapp.model;

public class FundTransfer {
    private Long sourceAccount;
    private Long destAccount;
    private double amount;

    public FundTransfer(Long sourceAccount, Long destAccount, double amount) {
        this.sourceAccount = sourceAccount;
        this.destAccount = destAccount;
        this.amount = amount;
    }

    public Long getSourceAccount() {
        return sourceAccount;
    }
    public void setSourceAccount(Long sourceAccount) {
        this.sourceAccount = sourceAccount;
    }
    public Long getDestAccount() {
        return destAccount;
    }
    public void setDestAccount(Long destAccount) {
        this.destAccount = destAccount;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    
}
