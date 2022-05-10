package com.finalledger.controllers;

import com.finalledger.models.User;
import com.finalledger.models.UserDocuments;
import com.finalledger.repositories.UserDocumentsRepository;
import com.finalledger.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

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
    public String showUserDocuments(Model model, Principal principal){

        model.addAttribute("documents", new UserDocuments());
        model.addAttribute("fileStackAPI", fileStackAPIKey);

        return principal == null ? "redirect:/login" : "/ledger/documents";
    }

    @PostMapping("/ledger/documents")
    public String saveUserDocuments (@ModelAttribute UserDocuments userDocument, @RequestParam(name = "title") String title, @RequestParam(name = "document_upload") String document_upload){

        // set the current user to the document
        User userLoggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDocument.setUser(userLoggedIn);
//        System.out.println("userLoggedIn = " + userLoggedIn.getId());
//
//        userDocument.setTitle(title);
//        System.out.println("title = " + title);
//
//        userDocument.setDocument_upload(document_upload);
//        System.out.println("document_upload = " + document_upload);


        userDocumentsDao.save(userDocument);

        return("redirect:/ledger/documents");
    }

}
