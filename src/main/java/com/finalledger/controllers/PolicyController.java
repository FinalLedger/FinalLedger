package com.finalledger.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PolicyController {

    @GetMapping("/policies/cookies")
    public String showCookies() {

        return "policies/cookies";
    }

    @GetMapping("/policies/disclaimer")
    public String showDisclaimer() {

        return "policies/disclaimer";
    }

    @GetMapping("/policies/eula")
    public String showEULA() {

        return "policies/eula";
    }

    @GetMapping("/policies/privacy")
    public String showPrivacy() {

        return "policies/privacy";
    }

    @GetMapping("/policies/terms")
    public String showTerms() {

        return "policies/terms";
    }

}
