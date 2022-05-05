package com.finalledger.controllers;

import com.finalledger.models.BankAccounts;
import com.finalledger.repositories.BankAccountsRepository;
import com.finalledger.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BankAccountsController {
    private final UserRepository userDao;
    private final BankAccountsRepository bankAccountsDao;

    public BankAccountsController(UserRepository userDao, BankAccountsRepository bankAccountsDao ) {
        this.userDao = userDao;
        this.bankAccountsDao = bankAccountsDao;
    }

    @GetMapping("/ledger/financial")
    public String showBankAccountsForm(Model model){
        model.addAttribute("bankAccounts", new BankAccounts());
        return "/ledger/financial";
    }

    @PostMapping("/ledger/financial")
    public String saveBankAccountsInformation(@ModelAttribute BankAccounts bankAccounts){

        return "redirect:/ledger/financial";
    }
}
