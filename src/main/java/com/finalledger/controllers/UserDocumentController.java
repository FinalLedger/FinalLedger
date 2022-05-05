package com.finalledger.controllers;

import com.finalledger.models.UserDocuments;
import com.finalledger.repositories.UserDocumentsRepository;
import com.finalledger.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserDocumentController {
    private final UserRepository userDao;
    private final UserDocumentsRepository userDocumentsDao;

    public UserDocumentController(UserRepository userDao, UserDocumentsRepository userDocumentsDao) {
        this.userDao = userDao;
        this.userDocumentsDao = userDocumentsDao;
    }

    @GetMapping("/ledger/documents")
    public String showUserDocuments(Model model){
        model.addAttribute("documents", new UserDocuments());

        return "ledger/documents";
    }

    @PostMapping("/ledger/documents")
    public String saveUserDocuments(@ModelAttribute UserDocuments userDocuments){

        return("redirect:/ledger/documents");
    }

}
