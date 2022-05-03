package com.finalledger.controllers;

import com.finalledger.repositories.UserRepository;

public class UserMedicalController {

    private final UserRepository userDao;

    public UserMedicalController(UserRepository userDao) {
        this.userDao = userDao;
    }


}
