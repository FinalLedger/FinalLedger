package com.finalledger.controllers;

import com.finalledger.models.Message;
import com.finalledger.models.User;
import com.finalledger.repositories.SiteContactRepository;
import com.finalledger.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.security.Principal;
import java.util.List;

@Controller
public class ProfileController {

    private UserRepository userDao;
    private SiteContactRepository siteContactDao;

    public ProfileController(UserRepository userDao, SiteContactRepository siteContactDao) {
        this.userDao = userDao;
        this.siteContactDao = siteContactDao;
    }

    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {

        if (principal != null) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            String mainUserMessage;
            if (user.isMainUser()){
                mainUserMessage = "Main User";
            } else {
                mainUserMessage = "Guest User";
            }

            List <User> userList = userDao.findAll();

            model.addAttribute("messagingDisplay", false);
            model.addAttribute("message", new Message());
            model.addAttribute("mainUserMessage", mainUserMessage);
            model.addAttribute("user", user);
            model.addAttribute("userList", userList);

            return "users/profile";
        }
        return "user/profile";
    }

    @GetMapping("/profile/{id}")
    public String goToSearchedProfile(@PathVariable long id, Model model){

        User user = userDao.getOne(id);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String mainUserMessage;
        if (user.isMainUser()){
            mainUserMessage = "Main User";
        } else {
            mainUserMessage = "Guest";
        }

        boolean contactAlready = !siteContactDao.findByOwner_userAndAdded_user_idExists(id, currentUser.getId()).isEmpty();

        model.addAttribute("contactAlready", contactAlready);
        model.addAttribute("message", new Message());
        model.addAttribute("profileID", id);
        model.addAttribute("messagingDisplay", true);
        model.addAttribute("mentorMessage", mainUserMessage);
        model.addAttribute("user", user);

        return "users/profile";

    }

}
