package com.finalledger.controllers;

import com.finalledger.models.User;
import com.finalledger.models.Contact;
import com.finalledger.repositories.UserContactsRepository;
import com.finalledger.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserContactsController {
    private final UserRepository userDao;
    private final UserContactsRepository userContactDao;

    public UserContactsController(UserRepository userDao, UserContactsRepository userContactDao) {
        this.userDao = userDao;
        this.userContactDao = userContactDao;
    }

    @GetMapping("/ledger/contacts")
    public String showUserContactsForm(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) {
            return "redirect:/login";
        }
        List<Contact> contactsList = user.getContacts();
        if (contactsList.isEmpty()) {
            model.addAttribute("existingList", false);
        } else {
            model.addAttribute("existingList", true);
            model.addAttribute("contactsList", contactsList);
        }
        model.addAttribute("newContact", new Contact());
        return "ledger/contacts";
    }

    @PostMapping("/ledger/contacts")
    public String saveUserContactsInformation(@ModelAttribute Contact newContact) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newContact.setUser(user);
        List<Contact> usersContacts = user.getContacts();
        usersContacts.add(newContact);
        userContactDao.save(newContact);
        return "redirect:/ledger/contacts";
    }

    @GetMapping("/ledger/contacts/{id}/edit")
    public String showEditContactForm(@PathVariable long id, Model model) {
        Contact editContact = userContactDao.getById(id);
        model.addAttribute("editContact", editContact);
        return "ledger/contacts_edit";
    }

    @PostMapping("/ledger/contacts/{id}/edit")
    public String editContact(@PathVariable long id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String relationship, @RequestParam String phoneNumber, @RequestParam String email, @RequestParam String primaryAddress, @ModelAttribute Contact editContact) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Contact> userContacts = user.getContacts();
        Contact updatedContact = userContactDao.getById(id);
        updatedContact.setFirstName(firstName);
        updatedContact.setLastName(lastName);
        updatedContact.setRelationship(relationship);
        updatedContact.setPhoneNumber(phoneNumber);
        updatedContact.setEmail(email);
        updatedContact.setPrimaryAddress(primaryAddress);
        userContactDao.save(updatedContact);
        for (Contact contact : userContacts) {
            if (contact.getId() == id) {
                int index = userContacts.indexOf(contact);
                userContacts.set(index, updatedContact);
            }
        }
        return "redirect:/ledger/contacts";
    }

    @PostMapping("/ledger/contacts/{id}/delete")
    public String deleteContact(@PathVariable long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Contact> userContacts = user.getContacts();
        Contact deletedContact = userContactDao.getById(id);
        userContacts.removeIf(contact -> contact.getId() == id);
        deletedContact.setUser(null);
        userContactDao.deleteById(id);
        return "redirect:/ledger/contacts";
    }
}
