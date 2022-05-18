package com.finalledger.controllers;

import com.finalledger.models.MedicalInformation;
import com.finalledger.models.User;
import com.finalledger.models.Documents;
import com.finalledger.repositories.UserDocumentsRepository;
import com.finalledger.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserDocumentController {

    @Value("${fileStackAPI}")
    public String fileStackAPIKey;

    private final UserRepository userDao;
    private final UserDocumentsRepository userDocumentsDao;

    public UserDocumentController(UserRepository userDao, UserDocumentsRepository userDocumentsDao) {
        this.userDao = userDao;
        this.userDocumentsDao = userDocumentsDao;
    }

    @GetMapping("/ledger/documents")
    public String showUserDocuments(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) {
            return "redirect:/login";
        }
        List<Documents> userDocs = user.getDocuments();
        if (userDocs.isEmpty()) {
            model.addAttribute("existingList", false);
        } else {
            model.addAttribute("existingList", true);
            model.addAttribute("userDocs", userDocs);
        }
        model.addAttribute("userDocument", new Documents());
        model.addAttribute("fileStackAPI", fileStackAPIKey);
        model.addAttribute("isGuestUser", false);
        return "ledger/documents";
    }

    @GetMapping("/ledger/{id}/documents")
    public String showConnectionContacts(@PathVariable long id, Model model) {
        User mainUser = userDao.getUserById(id);
        List<Documents> userDocs = mainUser.getDocuments();
        if (userDocs.isEmpty()) {
            model.addAttribute("existingList", false);
        } else {
            model.addAttribute("existingList", true);
            model.addAttribute("userDocs", userDocs);
        }
        model.addAttribute("mainUserId", id);
        model.addAttribute("mainUserName", mainUser.getUsername());
        model.addAttribute("isGuestUser", true);
        model.addAttribute("fileStackAPI", fileStackAPIKey);
        return "ledger/documents";
    }

    @PostMapping("/ledger/documents/{id}/delete")
    public String updatePersonal(@PathVariable long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Documents> userDocs = user.getDocuments();
        Documents deletedDoc = userDocumentsDao.getById(id);
        userDocs.removeIf(document -> document.getId() == id);
        deletedDoc.setUser(null);
        userDocumentsDao.deleteById(id);
        return "redirect:/ledger/documents";
    }

    @PostMapping("/ledger/documents")
    public String saveUserDocuments (@ModelAttribute Documents userDocument) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDocument.setUser(user);
        List<Documents> userDocs = user.getDocuments();
        userDocs.add(userDocument);
        userDocumentsDao.save(userDocument);
        return "redirect:/ledger/documents";
    }

}
