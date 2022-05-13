package com.finalledger.controllers;

import com.finalledger.models.User;
import com.finalledger.models.Contacts;
import com.finalledger.repositories.UserContactsRepository;
import com.finalledger.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class UserContactsController {
    private final UserRepository userDao;
    private final UserContactsRepository userContactDao;

    public UserContactsController(UserRepository userDao, UserContactsRepository userContactDao) {
        this.userDao = userDao;
        this.userContactDao = userContactDao;
    }

    @GetMapping("/ledger/contacts")
    public String showUserContactsForm(Model model, Principal principal){
        model.addAttribute("contacts", new Contacts());
        return principal == null ? "redirect:/login" : "/ledger/contacts";
    }

    @PostMapping("/ledger/contacts")
    public String saveUserContactsInformation(@ModelAttribute Contacts userContacts){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User persistUser = userDao.getById(user.getId());
        userContacts.setUser(persistUser);
        ArrayList<Contacts> document = new ArrayList<>();
        document.add(userContacts);
        userDao.save(persistUser);

        userContactDao.save(userContacts);

        return "redirect:/ledger/contacts";
    }


}
