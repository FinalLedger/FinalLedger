package com.finalledger.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LedgerController {
    @GetMapping("/main")
    public String showMain() {
        return "personal/main";
    }
}
