package com.finalledger.controllers;

import com.finalledger.repositories.UserRepository;

public class BankAccountsController {
    private final UserRepository userDao;

    public BankAccountsController(UserRepository userDao) {
        this.userDao = userDao;
    }
}
