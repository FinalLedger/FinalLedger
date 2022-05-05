package com.finalledger.controllers;
import com.finalledger.models.FinancialInvestment;
import com.finalledger.repositories.FinancialInvestmentRepository;
import com.finalledger.repositories.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class FinancialInvestmentController {
    private final UserRepository userDao;
    private final FinancialInvestmentRepository financialInvestmentDao;

    public FinancialInvestmentController(UserRepository userDao, FinancialInvestmentRepository financialInvestmentDao) {
        this.userDao = userDao;
        this.financialInvestmentDao = financialInvestmentDao;
    }
    @GetMapping("/ledger/financial")
    public String showFinancialForm(Model model){
        model.addAttribute("finance", new FinancialInvestment());
        return "/ledger/financial";
    }

    @PostMapping("/ledger/financial")
    public String saveFinancial(@ModelAttribute FinancialInvestment finance){


        return "redirect:/ledger/financial";
    }
}
