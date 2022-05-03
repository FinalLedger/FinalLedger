package com.finalledger.controllers;

import com.finalledger.repositories.UserRepository;

public class UserPoliciesController {

    private final UserRepository userDao;

    public UserPoliciesController(UserRepository userDao) {
        this.userDao = userDao;
    }
}
