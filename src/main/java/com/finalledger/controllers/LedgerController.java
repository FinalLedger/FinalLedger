package com.finalledger.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LedgerController {
    @GetMapping("/ledger/personal")
    public String showPersonal() {
        return "ledger/personal";
    }

    @GetMapping("/ledger/medical")
    public String showMedical() {
        return "ledger/medical";
    }

    @GetMapping("/ledger/contacts")
    public String showContacts() {
        return "ledger/contacts";
    }

    @GetMapping("/ledger/documents")
    public String showDocs() {
        return "ledger/documents";
    }

    @GetMapping("/ledger/financial")
    public String showFinancial() {
        return "ledger/financial";
    }
}
