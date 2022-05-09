package com.finalledger.controllers;

import com.finalledger.models.User;
import com.finalledger.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;

@Controller
public class UserController {

    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@RequestParam(name = "username") String username, @RequestParam(name = "email") String email, @RequestParam(name = "password") String password, @RequestParam(name = "confirmPass") String confirmPass, @RequestParam(name = "accountType") boolean isMainUser) {
//        if (!password.equals(confirmPass)) {
//            System.out.println("Password does not match, please try again.");
//            return "redirect:/register";
//        }
        String hash = passwordEncoder.encode(password);
        User user = new User(username, email, hash, isMainUser);
        userDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String showProfile() {
        return "users/profile";
    }

}
