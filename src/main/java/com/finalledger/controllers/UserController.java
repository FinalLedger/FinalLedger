package com.finalledger.controllers;

import com.finalledger.models.*;
import com.finalledger.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;
import java.security.Principal;
import java.util.ArrayList;

@Controller
public class UserController {

    @Value("${googleAPI}")
    public String googleAPIKey;

    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {

        model.addAttribute("googleAPI", googleAPIKey);

        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(Model model, @RequestParam(name = "username") String username, @RequestParam(name = "email") String email, @RequestParam(name = "password") String password, @RequestParam(name = "confirmPass") String confirmPass, @RequestParam(name = "accountType") boolean isMainUser) {
        if (userDao.findByUsername(username) != null) {
            boolean showErrorMsg = true;
            model.addAttribute("showErrorMsg", showErrorMsg);
            model.addAttribute("errorMsg", "An account with that username already exists.");
            return "users/register";
        } else if (userDao.findByEmail(email) != null) {
            boolean showErrorMsg = true;
            model.addAttribute("showErrorMsg", showErrorMsg);
            model.addAttribute("errorMsg", "An account with that email already exists.");
            return "users/register";
        } else {
            String hash = passwordEncoder.encode(password);
            User user = new User(username, email, hash, isMainUser);
            userDao.save(user);
        }
        return "redirect:/login";
    }

    @GetMapping("/profile")

    public String showProfile() { return "users/profile"; }



}
