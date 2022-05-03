package com.finalledger.controllers;

import com.finalledger.repositories.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class UserPersonalController {

    private final UserRepository userDao;


    public UserPersonalController(UserRepository userDao) {
        this.userDao = userDao;
    }

}
