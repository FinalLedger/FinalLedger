package com.finalledger.controllers;

import com.finalledger.models.User;
import com.finalledger.models.MedicalInformation;
import com.finalledger.repositories.UserMedicalRepository;
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
public class UserMedicalController {
    private final UserRepository userDao;
    private final UserMedicalRepository userMedicalDao;

    public UserMedicalController(UserRepository userDao, UserMedicalRepository userMedicalDao) {
        this.userDao = userDao;
        this.userMedicalDao = userMedicalDao;
    }

    @GetMapping("/ledger/medical")
    public String showUserMedicalForm(Model model, Principal principal) {
        model.addAttribute("medical", new MedicalInformation());
        return principal == null ? "redirect:/login" : "/ledger/medical";
    }

    @PostMapping("/ledger/medical")
    public String saveMedicalInformation(@ModelAttribute MedicalInformation userMedicalInformation) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User persistUser = userDao.getById(user.getId());
        userMedicalInformation.setUser(persistUser);
        ArrayList<MedicalInformation> document = new ArrayList<>();
        document.add(userMedicalInformation);
        userDao.save(persistUser);

        userMedicalDao.save(userMedicalInformation);

        return"redirect:/ledger/medical";
    }

}
