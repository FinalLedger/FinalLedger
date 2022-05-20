package com.finalledger.controllers;

import com.finalledger.models.*;
import com.finalledger.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;

    @Value("${fileStackAPI}")
    private String fileStackAPIKey;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("fileStackAPI", fileStackAPIKey);
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(Model model, @ModelAttribute User user) {
        if (userDao.findByUsername(user.getUsername()) != null) {
            model.addAttribute("showErrorMsg", true);
            model.addAttribute("errorMsg", "An account with that username already exists.");
            return "users/register";
        } else if (userDao.findByEmail(user.getEmail()) != null) {
            model.addAttribute("showErrorMsg", true);
            model.addAttribute("errorMsg", "An account with that email already exists.");
            return "users/register";
        } else {
            String hash = passwordEncoder.encode(user.getPassword());
            user.setPassword(hash);
            if (user.getProfilePicUrl().length() == 0) {
                user.setProfilePicUrl("https://picsum.photos/200/300"); // random pic generator
            }
            userDao.save(user);
        }
        return "redirect:/login";
    }
}
