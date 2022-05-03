package com.finalledger.controllers;

public class CreditCardController {
    private final CreditCardController userDao;

    public CreditCardController(CreditCardController userDao) {
        this.userDao = userDao;
    }
}
