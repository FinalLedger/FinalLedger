package com.finalledger.controllers;

import com.finalledger.models.User;
import com.finalledger.models.UserMedicalInformation;
import com.finalledger.repositories.UserMedicalRepository;
import com.finalledger.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserMedicalController {
    private final UserRepository userDao;
    private final UserMedicalRepository userMedicalDao ;

    public UserMedicalController(UserRepository userDao, UserMedicalRepository userMedicalDao) {
        this.userDao = userDao;
        this.userMedicalDao = userMedicalDao;
    }

    @GetMapping("/ledger/medical")
    public String showUserMedicalForm(Model model){
        model.addAttribute("medical", new UserMedicalInformation());
        return("ledger/medical");
    }

    @PostMapping("/ledger/medical")
    public String saveMedicalInformation(@ModelAttribute UserMedicalInformation userMedicalInformation){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userMedicalDao.save(user);
        return"redirect:/ledger/medical";
    }

}
