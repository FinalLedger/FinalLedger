package com.finalledger.controllers;

import com.finalledger.repositories.UserRepository;

public class CreditCardController {
    private final UserRepository userDao;
    public CreditCardController(UserRepository userDao) {
        this.userDao = userDao;
    }
}
