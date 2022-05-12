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

import java.security.Principal;
import java.util.ArrayList;

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

        model.addAttribute("documents", new Documents());
        model.addAttribute("fileStackAPI", fileStackAPIKey);

        return principal == null ? "redirect:/login" : "/ledger/documents";
    }

    @PostMapping("/ledger/documents/{id}/delete")
    public String updatePersonal(@PathVariable Long id){
        userDocumentsDao.deleteById(id);

        return "redirect:/ledger/documents";
    }

    @PostMapping("/ledger/documents")
    public String saveUserDocuments (@ModelAttribute Documents userDocument, @RequestParam(name = "title") String title, @RequestParam(name = "document_upload") String document_upload){

        // set the current user to the document
        User userLoggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User persistUser = userDao.getById(userLoggedIn.getId());

        userDocument.setUser(persistUser);

        ArrayList<Documents> documents = new ArrayList<>();
        documents.add(userDocument);
        persistUser.setDocuments(documents);
        userDao.save(persistUser);

        return("redirect:/ledger/documents");
    }

}
