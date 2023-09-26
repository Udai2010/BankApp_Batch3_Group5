package com.bankapp.bankapp.exception;

public class AccountDisabledException  extends RuntimeException{
    public AccountDisabledException(String message){
        super(message);
    }
}
