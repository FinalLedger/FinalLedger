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
import java.util.Objects;

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
    public String showFinancialForm(Model model, Principal principal) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) {
            return "redirect:/login";
        }
        FinancialInvestment financeInfo = financialInvestmentDao.findByUserId(user.getId());
        InsurancePolicy insurancePolicy = insurancePolicyDao.findByUserId(user.getId());
        BankAccounts bankAccounts = bankAccountsDao.findByUserId(user.getId());
        CreditCard creditCard = creditCardDao.findByUserId(user.getId());
//        if (financeInfo == null || insurancePolicy == null || bankAccounts == null || creditCard == null) {
//        if (insurancePolicy == null) {
//            model.addAttribute("existingInfo", false);
////            model.addAttribute("financeInfo", new FinancialInvestment());
//            model.addAttribute("insurancePolicy", new InsurancePolicy());
////            model.addAttribute("bankAccounts", new BankAccounts());
////            model.addAttribute("creditCard", new CreditCard());
//        } else {
//            model.addAttribute("existingInfo", true);
////            model.addAttribute("financeInfo", financeInfo);
//            model.addAttribute("insurancePolicy", new InsurancePolicy());
////            model.addAttribute("bankAccounts", new BankAccounts());
////            model.addAttribute("creditCard", new CreditCard());
//        }
//        return "ledger/financial";
//    }
        List<InsurancePolicy> insurancePolicyList = user.getInsurancePolicy();
        if (insurancePolicyList.isEmpty()) {
            model.addAttribute("existingList", false);
        }else{
            model.addAttribute("existingList", true);
            model.addAttribute("insurancePolicyList", insurancePolicyList);
        }
        model.addAttribute("newInsurance", new InsurancePolicy());
        return "ledger/financial";
    }

    @PostMapping("/ledger/financial/{id}/edit")
    public String updateFinancial(@PathVariable long id,@RequestParam String company,@RequestParam String current_value, @RequestParam String beneficiary,@RequestParam String contact_info){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        FinancialInvestment financeInfo = financialInvestmentDao.getById(id);
        financeInfo.setCompany(company);
        financeInfo.setCurrent_value(current_value);
        financeInfo.setBeneficiary(beneficiary);
        financeInfo.setContact_info(contact_info);
        financeInfo.setUser(user);
        financialInvestmentDao.save(financeInfo);
        return "redirect:/ledger/financial/";
    }

    @PostMapping("/ledger/financial/{id}/delete")
    public String deleteFinancial(@PathVariable Long id){
        financialInvestmentDao.deleteById(id);
        return "redirect:/ledger/financial";
    }
    @GetMapping("/ledger/insurancePolicy/{id}/edit")
    public String showEditInsurancePolicy(@PathVariable long id, Model model) {
        InsurancePolicy editInsurance = insurancePolicyDao.getById(id);
        model.addAttribute("editInsurance", editInsurance);
        return "ledger/insurancePolicy-edit";
    }
    @PostMapping("/ledger/insurancePolicy")
    public String saveInsurancePolicyInformation(@ModelAttribute InsurancePolicy newInsurance){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User persistUser = userDao.getById(user.getId());
        newInsurance.setUser(persistUser);

        InsurancePolicy existingInfoUser = insurancePolicyDao.findByUserId(persistUser.getId());
        if (existingInfoUser != null) {

            return "redirect:/ledger/financial";

        }

        ArrayList<InsurancePolicy> insurancePolicyList = new ArrayList<>();
        insurancePolicyList.add(newInsurance);
        userDao.save(persistUser);

        insurancePolicyDao.save(newInsurance);

        return "redirect:/ledger/financial";
    }
    @PostMapping("/ledger/insurancePolicy/{id}/edit")
    public String editInsurancePolicyForm(@PathVariable long id,@RequestParam String company,@RequestParam String contactInfo,@RequestParam String currentValue, @RequestParam String beneficiary){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<InsurancePolicy> insurancePolicyList = user.getInsurancePolicy();
        InsurancePolicy newInsurance = insurancePolicyDao.getById(id);
        newInsurance.setCompany(company);
        newInsurance.setContactInfo(contactInfo);
        newInsurance.setCurrentValue(currentValue);
        newInsurance.setBeneficiary(beneficiary);
//        newInsurance.setUser(user);
        insurancePolicyDao.save(newInsurance);
        for (InsurancePolicy insurancePolicy : insurancePolicyList) {
            if (insurancePolicy.getId() == id) {
                int index = insurancePolicyList.indexOf(insurancePolicy);
                insurancePolicyList.set(index, newInsurance);
            }
        }
        return "redirect:/ledger/financial";
    }

    @PostMapping("/ledger/insurancePolicy/{id}/delete")
    public String deleteInsurancePolicy(@PathVariable Long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<InsurancePolicy> insuranceList = user.getInsurancePolicy();
        InsurancePolicy deletedInsurance = insurancePolicyDao.getById(id);
        insuranceList.removeIf(insurance -> Objects.equals(insurance.getId(), id));
        deletedInsurance.setUser(null);
        insurancePolicyDao.deleteById(id);
        return "redirect:/ledger/financial";
    }

    @PostMapping("ledger/bankAccounts/{id}/edit")
    public String editBankAccountsForm(@PathVariable long id,@RequestParam  String contactsInfo,@RequestParam String checkingAccount,@RequestParam String savingAccount){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BankAccounts bankAccounts = bankAccountsDao.getById(id);
        bankAccounts.setContactInfo(contactsInfo);
        bankAccounts.setCheckingAccount(checkingAccount);
        bankAccounts.setSavingAccount(savingAccount);
        bankAccounts.setUser(user);
        bankAccountsDao.save(bankAccounts);
        return "redirect:/ledger/financial/";
    }
    @PostMapping("/ledger/bankAccounts/{id}/delete")
    public String deleteBankAccount(@PathVariable Long id){
        bankAccountsDao.deleteById(id);
        return "redirect:/ledger/financial";
    }

    @PostMapping("ledger/creditCard/{id}/edit")
    public String editCreditCardForm(@PathVariable long id,@RequestParam  String type,@RequestParam  String issuer){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CreditCard creditCard = creditCardDao.getById(id);
        creditCard.setType(type);
        creditCard.setIssuer(issuer);
        creditCard.setUser(user);
        creditCardDao.save(creditCard);
        return "redirect:/ledger/financial";
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
