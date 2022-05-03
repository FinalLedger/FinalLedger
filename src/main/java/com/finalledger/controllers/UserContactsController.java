package com.finalledger.controllers;

import com.finalledger.repositories.UserRepository;

public class UserContactsController {

    private final UserRepository userDao;

    public UserContactsController(UserRepository userDao) {
        this.userDao = userDao;
    }


}
