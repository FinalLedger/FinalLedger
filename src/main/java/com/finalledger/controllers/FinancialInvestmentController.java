package com.finalledger.controllers;

import com.finalledger.repositories.UserRepository;

public class FinancialInvestmentController {
    private final UserRepository userDao;

    public FinancialInvestmentController(UserRepository userDao) {
        this.userDao = userDao;
    }
}
