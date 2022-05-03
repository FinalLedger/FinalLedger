package com.finalledger.controllers;

import com.finalledger.repositories.UserRepository;

public class UserCreditCardController {

    private final UserRepository userDao;

    public UserCreditCardController(UserRepository userDao) {
        this.userDao = userDao;
    }
}