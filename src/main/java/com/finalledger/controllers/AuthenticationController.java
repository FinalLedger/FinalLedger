package com.finalledger.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    @Value("${googleAPI}")
    public String googleAPIKey;

    @GetMapping("/login")
    public String showLoginForm(Model model) {

        model.addAttribute("googleAPI", googleAPIKey);

        return "users/login";
    }
}



