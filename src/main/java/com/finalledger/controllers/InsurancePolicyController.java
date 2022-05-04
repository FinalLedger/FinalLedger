package com.finalledger.controllers;

import com.finalledger.repositories.UserRepository;

public class InsurancePolicyController {

    private final UserRepository userDao;

    public InsurancePolicyController(UserRepository userDao) {
        this.userDao = userDao;
    }
}
