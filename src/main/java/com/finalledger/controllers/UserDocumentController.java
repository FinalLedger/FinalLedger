package com.finalledger.controllers;

import com.finalledger.repositories.UserRepository;

public class UserDocumentController {

    private final UserRepository userDao;

    public UserDocumentController(UserRepository userDao) {
        this.userDao = userDao;
    }
}
