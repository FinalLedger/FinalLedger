package com.finalledger.controllers;
import com.finalledger.models.*;
import com.finalledger.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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

        model.addAttribute("insurancePolicy", new InsurancePolicy());

        model.addAttribute("bankAccounts", new BankAccounts());

        model.addAttribute("creditCard", new CreditCard());

        return principal == null ?  "redirect:/login" : "/ledger/financial";
    }
    @GetMapping("/ledger/financial/{id}/edit")
    public String editFinancialForm(@PathVariable long id, Model model, Principal principal){

        model.addAttribute("editFinance", financialInvestmentDao.getById(id));

        model.addAttribute("editInsurancePolicy", insurancePolicyDao.getById(id));

        model.addAttribute("editBankAccounts", bankAccountsDao.getById(id));

        model.addAttribute("editCreditCard", creditCardDao.getById(id));


        return principal == null ?  "redirect:/login" : "/ledger/financial";
    }

    @PostMapping("/ledger/financial/{id}/edit")
    public String updateFianncial(@PathVariable long id,@RequestParam String company,@RequestParam String current_value, @RequestParam String beneficiary,@RequestParam String contact_info ,@RequestParam User user ){
       FinancialInvestment financialInvestment = financialInvestmentDao.getById(id);
       financialInvestment.setCompany(company);
       financialInvestment.setCurrent_value(current_value);
       financialInvestment.setBeneficiary(beneficiary);
       financialInvestment.setContact_info(contact_info);
       financialInvestment.setUser(user);


        return "redirect:/ledger/financial/";
    }

    @PostMapping("/ledger/financial/{id}/delete")
    public String deleteFinancial(@PathVariable Long id){
        financialInvestmentDao.deleteById(id);

        return "redirect:/ledger/financial";
    }

    @PostMapping("/ledger/insurancePolicy/{id}/edit")
    public String editInsurancePolicyForm(@PathVariable long id,@RequestParam String company,@RequestParam String contactInfo,@RequestParam String currentValue, @RequestParam String beneficiary, @RequestParam User user){
        InsurancePolicy insurancePolicy = insurancePolicyDao.getById(id);
        insurancePolicy.setCompany(company);
        insurancePolicy.setContactInfo(contactInfo);
        insurancePolicy.setCurrentValue(currentValue);
        insurancePolicy.setBeneficiary(beneficiary);
        insurancePolicy.setUser(user);


        return "redirect:/ledger/financial/";
    }
    @PostMapping("/ledger/insurancePolicy/{id}/delete")
    public String deleteInsurancePolicy(@PathVariable Long id){
        insurancePolicyDao.deleteById(id);

        return "redirect:/ledger/fianncial";
    }
    @PostMapping("ledger/bankAccounts/{id}/edit")
    public String editBankAccountsForm(@PathVariable long id,@RequestParam  String contactsInfo,@RequestParam String checkingAccount,@RequestParam String savingAccount,@RequestParam User user){
    BankAccounts bankAccounts = bankAccountsDao.getById(id);
    bankAccounts.setContactInfo(contactsInfo);
    bankAccounts.setCheckingAccount(checkingAccount);
    bankAccounts.setSavingAccount(savingAccount);
    bankAccounts.setUser(user);


        return "redirect:/ledger/financial/";
    }
    @PostMapping("/ledger/bankAccounts/{id}/delete")
    public String deleteBankAccount(@PathVariable Long id){
        bankAccountsDao.deleteById(id);

        return "redirect:/ledger/financial";
    }

    @PostMapping("ledger/creditCard/{id}/edit")
    public String editCreditCardForm(@PathVariable long id,@RequestParam  String type,@RequestParam  String issuer , @RequestParam User user){
        CreditCard creditCard = creditCardDao.getById(id);
        creditCard.setType(type);
        creditCard.setIssuer(issuer);
        creditCard.setUser(user);


        return "redirect:/ledger/financial/";
    }

    @PostMapping("/ledger/creditCard/{id}/delete")
    public String deleteCreditCard(@PathVariable Long id){
        creditCardDao.deleteById(id);

        return "redirect:/ledger/financial";
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

        return "redirect:/ledger/financial";
    }
    @PostMapping("/ledger/insurancePolicy")
    public String saveInsurancePolicyInformation(@ModelAttribute InsurancePolicy insurancePolicy){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User persistUser = userDao.getById(user.getId());
        insurancePolicy.setUser(persistUser);
        ArrayList<InsurancePolicy> document = new ArrayList<>();

        document.add(insurancePolicy);
        userDao.save(persistUser);

        insurancePolicyDao.save(insurancePolicy);

        return "redirect:/ledger/financial";
    }
    @PostMapping("/ledger/bankAccounts")
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
    @PostMapping("/ledger/creditCard")
    public String saveCreditCardInformation(@ModelAttribute CreditCard creditCard){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User persistUser = userDao.getById(user.getId());
        creditCard.setUser(persistUser);
        ArrayList<CreditCard> document = new ArrayList<>();

        document.add(creditCard);
        userDao.save(persistUser);

        creditCardDao.save(creditCard);

        return "redirect:/ledger/financial";
    }
}
