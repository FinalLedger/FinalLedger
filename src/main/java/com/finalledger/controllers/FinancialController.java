package com.finalledger.controllers;
import com.finalledger.models.BankAccounts;
import com.finalledger.models.CreditCard;
import com.finalledger.models.FinancialInvestment;
import com.finalledger.models.InsurancePolicy;
import com.finalledger.repositories.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String showFinancialForm(Model model){
        model.addAttribute("finance", new FinancialInvestment());
        return "ledger/financial";
    }
    public String showInsurancePolicyForm(Model model){
        model.addAttribute("insurancePolicy", new InsurancePolicy());
        return "/ledger/financial";
    }
    public String showBankAccountsForm(Model model){
        model.addAttribute("bankAccounts", new BankAccounts());
        return "/ledger/financial";
    }
    public String showCreditCardForm(Model model){
        model.addAttribute("creditCard", new CreditCard());
        return "/ledger/financial";
    }

    @PostMapping("/ledger/financial"
    public String saveFinancialInformation(@ModelAttribute FinancialInvestment finance){

        return "redirect:ledger/financial";
    }
    public String saveInsurancePolicyInformation(@ModelAttribute InsurancePolicy insurancePolicy){


        return "redirect:/ledger/financial";
    }
    public String saveBankAccountsInformation(@ModelAttribute BankAccounts bankAccounts){

        return "redirect:/ledger/financial";
    }
    public String saveCreditCardInformation(@ModelAttribute CreditCard creditCard){

        return "redirect:/ledger/financial";
    }
}
