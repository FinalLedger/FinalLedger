package com.finalledger.controllers;
import com.finalledger.models.*;
import com.finalledger.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class FinancialController {
    private final UserRepository userDao;
    private final FinancialInvestmentRepository financialInvestmentDao;
    private final InsurancePolicyRepository insurancePolicyDao;
    private final BankAccountsRepository bankAccountsDao;
    private final CreditCardRepository creditCardDao;

    public FinancialController(UserRepository userDao, FinancialInvestmentRepository financialInvestmentDao, InsurancePolicyRepository insurancePolicyDao, BankAccountsRepository bankAccountsDao, CreditCardRepository creditCardDao ) {
        this.userDao = userDao;
        this.financialInvestmentDao = financialInvestmentDao;
        this.insurancePolicyDao = insurancePolicyDao;
        this.bankAccountsDao = bankAccountsDao;
        this.creditCardDao = creditCardDao;
    }
    @GetMapping("/ledger/financial")
    public String showFinancialForm(Model model, Principal principal){
        model.addAttribute("finance", new FinancialInvestment());
        return principal == null ?  "redirect:/login" : "/ledger/financial";
    }
    public String showInsurancePolicyForm(Model model, Principal principal){
        model.addAttribute("insurancePolicy", new InsurancePolicy());
        return principal == null ?  "redirect:/login" : "/ledger/financial";
    }
    public String showBankAccountsForm(Model model, Principal principal){
        model.addAttribute("bankAccounts", new BankAccounts());
        return principal == null ?  "redirect:/login" : "/ledger/financial";
    }
    public String showCreditCardForm(Model model, Principal principal){
        model.addAttribute("creditCard", new CreditCard());
        return principal == null ?  "redirect:/login" : "/ledger/financial";
    }

    @PostMapping("/ledger/financial")
    public String saveFinancialInformation(@ModelAttribute FinancialInvestment finance){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User persistUser = userDao.getById(user.getId());
        finance.setUser(persistUser);
        ArrayList<FinancialInvestment> document = new ArrayList<>();

        document.add(finance);
        userDao.save(persistUser);

        financialInvestmentDao.save(finance);

        return "redirect:ledger/financial";
    }
    public String saveInsurancePolicyInformation(@ModelAttribute InsurancePolicy insurancePolicy){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User persistUser = userDao.getById(user.getId());
        insurancePolicy.setUser(persistUser);
        ArrayList<InsurancePolicy> document = new ArrayList<>();

        document.add(insurancePolicy);
        userDao.save(persistUser);

        insurancePolicyDao.save(insurancePolicy);

        return "redirect:ledger/financial";
    }
    public String saveBankAccountsInformation(@ModelAttribute BankAccounts bankAccounts){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User persistUser = userDao.getById(user.getId());
        bankAccounts.setUser(persistUser);
        ArrayList<BankAccounts> document = new ArrayList<>();

        document.add(bankAccounts);
        userDao.save(persistUser);

        bankAccountsDao.save(bankAccounts);

        return "redirect:/ledger/financial";
    }
    public String saveCreditCardInformation(@ModelAttribute CreditCard creditCard){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User persistUser = userDao.getById(user.getId());
        creditCard.setUser(persistUser);
        ArrayList<CreditCard> document = new ArrayList<>();

        document.add(creditCard);
        userDao.save(persistUser);

        return "redirect:/ledger/financial";
    }
}
