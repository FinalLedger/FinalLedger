package com.finalledger.controllers;

import com.finalledger.models.UserPersonalInformation;
import com.finalledger.repositories.UserPersonalRepository;
import com.finalledger.repositories.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class UserPersonalController {

    private final UserRepository userDao;
    private final UserPersonalRepository userPersonalDao;

    public UserPersonalController(UserRepository userDao, UserPersonalRepository userPersonalDao) {
        this.userDao = userDao;
        this.userPersonalDao = userPersonalDao;
    }

    @GetMapping("/ledger/personal")
    public String showUserPersonalForm(Model model) {
        model.addAttribute("personal", new UserPersonalInformation());
        return "/ledger/personal";
    }

    @PostMapping("/ledger/personal")
    public String saveUserPersonalInformation(@ModelAttribute UserPersonalInformation userPersonalInformation){

        return"redirect:/ledger/personal";
    }

}
