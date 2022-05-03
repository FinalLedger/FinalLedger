package com.finalledger.controllers;

import com.finalledger.repositories.UserRepository;

public class UserInsurancePolicyController {

    private final UserRepository userDao;

    public UserInsurancePolicyController(UserRepository userDao) {
        this.userDao = userDao;
    }
}
