package com.finalledger.controllers;

import com.finalledger.models.User;
import com.finalledger.models.UserPersonalInformation;
import com.finalledger.repositories.UserPersonalRepository;
import com.finalledger.repositories.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class UserPersonalController {

    private final UserRepository userDao;
    private final UserPersonalRepository userPersonalDao;

    public UserPersonalController(UserRepository userDao, UserPersonalRepository userPersonalDao) {
        this.userDao = userDao;
        this.userPersonalDao = userPersonalDao;
    }

    @GetMapping("/ledger/personal")
    public String showUserPersonalForm(Model model, Principal principal) {
        model.addAttribute("userPersonalInformation", new UserPersonalInformation());
//        return "/ledger/personal";
        return principal == null ?  "redirect:/login" : "/ledger/personal";
    }

    @PostMapping("/ledger/personal")
    public String saveUserPersonalInformation(@ModelAttribute UserPersonalInformation userPersonalInformation){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User persistUser = userDao.getById(user.getId());
        userPersonalInformation.setUser(persistUser);
        ArrayList<UserPersonalInformation> document = new ArrayList<>();
        document.add(userPersonalInformation);
        userDao.save(persistUser);

        userPersonalDao.save(userPersonalInformation);

        return"redirect:/ledger/personal";
    }
}
