package com.finalledger.controllers;

import com.finalledger.repositories.UserRepository;

public class UserInvestmentController{

    private final UserRepository userDao;

    public UserInvestmentController(UserRepository userDao) {
        this.userDao = userDao;
    }
}
