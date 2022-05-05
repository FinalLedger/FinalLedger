package com.finalledger.controllers;

import com.finalledger.models.UserContacts;
import com.finalledger.repositories.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class UserContactsController {
    private final UserRepository userDao;
    private final UserContactsController userContactDao;

    public UserContactsController(UserRepository userDao, UserContactsController userContactDao) {
        this.userDao = userDao;
        this.userContactDao = userContactDao;
    }

    @GetMapping("/ledger/contacts")
    public String showUserContactsForm(Model model){
        model.addAttribute("contacts", new UserContacts());
        return "/ledger/contacts";
    }

    @PostMapping("/ledger/contacts")
    public String saveUserContactsInformation(@ModelAttribute UserContacts userContacts){

        return "redirect:/ledger/contacts";
    }


}
