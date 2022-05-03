package com.finalledger.controllers;

public class BankAccounts {
    private final CreditCardController userDao;

    public BankAccounts(CreditCardController userDao) {
        this.userDao = userDao;
    }
}
