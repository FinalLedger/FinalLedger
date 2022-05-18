package com.finalledger.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String showLoginForm(Model model) {

        return "users/login";
    }

    @GetMapping("/logout")
    public ModelAndView logOut(HttpServletRequest request) {

        HttpSession session= request.getSession(false);
        SecurityContextHolder.clearContext();
        if(session != null) {
            session.invalidate();
        }
        return new ModelAndView("redirect:/login");
    }
}



