package com.finalledger.controllers;

import com.finalledger.models.UserContacts;
import com.finalledger.repositories.UserContactsRepository;
import com.finalledger.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserContactsController {
    private final UserRepository userDao;
    private final UserContactsRepository userContactDao;

    public UserContactsController(UserRepository userDao, UserContactsRepository userContactDao) {
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
