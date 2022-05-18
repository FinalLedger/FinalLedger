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
    public String showFinancialForm(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) {
            return "redirect:/login";
        }
        List<InsurancePolicy> insuranceList = user.getInsurancePolicy();
        if (insuranceList.isEmpty()) {
            model.addAttribute("existingInsurance", false);
        } else {
            model.addAttribute("existingInsurance", true);
            model.addAttribute("insuranceList", insuranceList);
        }
        model.addAttribute("newInsurance", new InsurancePolicy());

        List<FinancialInvestment> financialInvestmentList = user.getFinancialInvestments();
        if (financialInvestmentList.isEmpty()) {
            model.addAttribute("existingInvestment", false);
        } else {
            model.addAttribute("existingInvestment", true);
            model.addAttribute("financialInvestmentList", financialInvestmentList);
        }
        model.addAttribute("newFinance", new FinancialInvestment());

        List<BankAccounts> bankAccountsList = user.getBankAccounts();
        if (bankAccountsList.isEmpty()) {
            model.addAttribute("existingBankAccount", false);
        } else {
            model.addAttribute("existingBankAccount", true);
            model.addAttribute("bankAccountsList", bankAccountsList);
        }
        model.addAttribute("newBankAccount", new BankAccounts());

        List<CreditCard> creditCardList = user.getCreditCards();
        if (creditCardList.isEmpty()) {
            model.addAttribute("existingCreditCard", false);
        } else {
            model.addAttribute("existingCreditCard", true);
            model.addAttribute("creditCardList", creditCardList);
        }
        model.addAttribute("newCreditCard", new CreditCard());
        model.addAttribute("isGuestUser", false);
        return "ledger/financial";
    }

    @GetMapping("/ledger/{id}/financial")
    public String showConnectionFinancial(@PathVariable long id, Model model) {
        User mainUser = userDao.getUserById(id);
        List<InsurancePolicy> insuranceList = mainUser.getInsurancePolicy();
        if (insuranceList.isEmpty()) {
            model.addAttribute("existingInsurance", false);
        } else {
            model.addAttribute("existingInsurance", true);
            model.addAttribute("insuranceList", insuranceList);
        }
        List<FinancialInvestment> financialInvestmentList = mainUser.getFinancialInvestments();
        if (financialInvestmentList.isEmpty()) {
            model.addAttribute("existingInvestment", false);
        } else {
            model.addAttribute("existingInvestment", true);
            model.addAttribute("financialInvestmentList", financialInvestmentList);
        }
        List<BankAccounts> bankAccountsList = mainUser.getBankAccounts();
        if (bankAccountsList.isEmpty()) {
            model.addAttribute("existingBankAccount", false);
        } else {
            model.addAttribute("existingBankAccount", true);
            model.addAttribute("bankAccountsList", bankAccountsList);
        }
        List<CreditCard> creditCardList = mainUser.getCreditCards();
        if (creditCardList.isEmpty()) {
            model.addAttribute("existingCreditCard", false);
        } else {
            model.addAttribute("existingCreditCard", true);
            model.addAttribute("creditCardList", creditCardList);
        }
        model.addAttribute("mainUserId", id);
        model.addAttribute("mainUserName", mainUser.getUsername());
        model.addAttribute("isGuestUser", true);
        return "ledger/financial";
    }

    @GetMapping("/ledger/insurancePolicy/{id}/edit")
    public String showEditInsurancePolicy(@PathVariable long id, Model model) {
        InsurancePolicy editInsurance = insurancePolicyDao.getById(id);
        model.addAttribute("editInsurance", editInsurance);

        return "ledger/insurance_edit";

    }
    @PostMapping("/ledger/insurancePolicy")
    public String saveInsurancePolicyInformation(@ModelAttribute InsurancePolicy newInsurance){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        newInsurance.setUser(user);
        List<InsurancePolicy> insuranceList = user.getInsurancePolicy();
        insuranceList.add(newInsurance);

        insurancePolicyDao.save(newInsurance);
        return "redirect:/ledger/financial";
    }

    @PostMapping("/ledger/insurancePolicy/{id}/edit")
    public String editInsurancePolicyForm(@PathVariable long id, @RequestParam String company, @RequestParam String contactInfo, @RequestParam String currentValue, @RequestParam String beneficiary, @ModelAttribute InsurancePolicy editInsurance) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<InsurancePolicy> insurancePolicyList = user.getInsurancePolicy();
        InsurancePolicy newInsurance = insurancePolicyDao.getById(id);
        newInsurance.setCompany(company);
        newInsurance.setContactInfo(contactInfo);
        newInsurance.setCurrentValue(currentValue);
        newInsurance.setBeneficiary(beneficiary);

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

        List<InsurancePolicy> insurancePolicyList = user.getInsurancePolicy();
        InsurancePolicy deletedInsurance = insurancePolicyDao.getById(id);
        insurancePolicyList.removeIf(insurancePolicy -> insurancePolicy.getId() == id);

        deletedInsurance.setUser(null);
        insurancePolicyDao.deleteById(id);
        return "redirect:/ledger/financial";
    }
//<----------------------------------------------------------- Financial Info ------------------------------------------------------------------->
    @GetMapping("/ledger/financial/{id}/edit")
    public String showEditFinancial(@PathVariable long id, Model model) {
        FinancialInvestment editFinance = financialInvestmentDao.getById(id);
        model.addAttribute("editFinance", editFinance);

        return "ledger/financial_edit";
    }

    @PostMapping("/ledger/financial")
    public String saveFinancialInformation(@ModelAttribute FinancialInvestment newFinance){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User persistUser = userDao.getById(user.getId());
        newFinance.setUser(user);
        List<FinancialInvestment> financialInvestmentList = user.getFinancialInvestments();

        financialInvestmentList.add(newFinance);
//        userDao.save(persistUser);

        financialInvestmentDao.save(newFinance);

        return "redirect:/ledger/financial";
    }

    @PostMapping("/ledger/financial/{id}/edit")
    public String updateFinancial(@PathVariable long id,@RequestParam String company,@RequestParam String currentValue, @RequestParam String beneficiary,@RequestParam String contactInfo){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<FinancialInvestment> financialInvestmentsList = user.getFinancialInvestments();
        FinancialInvestment newFinanceInfo = financialInvestmentDao.getById(id);
        newFinanceInfo.setCompany(company);
        newFinanceInfo.setCurrentValue(currentValue);
        newFinanceInfo.setBeneficiary(beneficiary);
        newFinanceInfo.setContactInfo(contactInfo);

        financialInvestmentDao.save(newFinanceInfo);
        for (FinancialInvestment finance : financialInvestmentsList) {
            if (finance.getId() == id) {
                int index = financialInvestmentsList.indexOf(finance);
                financialInvestmentsList.set(index, newFinanceInfo);
            }
        }
        return "redirect:/ledger/financial/";
    }

    @PostMapping("/ledger/financial/{id}/delete")
    public String deleteFinancial(@PathVariable Long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<FinancialInvestment>  financialInvestmentsList = user.getFinancialInvestments();
        FinancialInvestment deletedFinancial = financialInvestmentDao.getById(id);
        financialInvestmentsList.removeIf(insurancePolicy -> insurancePolicy.getId() == id);

        deletedFinancial.setUser(null);
        financialInvestmentDao.deleteById(id);

        return "redirect:/ledger/financial";
    }
    //<----------------------------------------------------------- Bank Account Info ------------------------------------------------------------------->
    @GetMapping("/ledger/bankAccounts/{id}/edit")
    public String showEditBank(@PathVariable long id, Model model) {
        BankAccounts editBank = bankAccountsDao.getById(id);
        model.addAttribute("editBank", editBank);

        return "ledger/bank_edit";
    }

    @PostMapping("/ledger/bankAccounts")
    public String saveBankAccountsInformation(@ModelAttribute BankAccounts newBankAccount){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        newBankAccount.setUser(user);
        List<BankAccounts> bankAccountsList = user.getBankAccounts();
        bankAccountsList.add(newBankAccount);

        bankAccountsDao.save(newBankAccount);

        return "redirect:/ledger/financial";
    }

    @PostMapping("/ledger/bankAccounts/{id}/edit")
    public String editBankAccountsForm(@PathVariable long id,@RequestParam String company, @RequestParam  String contactInfo ,@RequestParam String checkingAccount,@RequestParam String savingAccount, @ModelAttribute BankAccounts editBank){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<BankAccounts> bankAccountsList = user.getBankAccounts();
        BankAccounts newBankAccounts = bankAccountsDao.getById(id);
        newBankAccounts.setCompany(company);
        newBankAccounts.setContactInfo(contactInfo);
        newBankAccounts.setCheckingAccount(checkingAccount);
        newBankAccounts.setSavingAccount(savingAccount);

        bankAccountsDao.save(newBankAccounts);
        for (BankAccounts bank : bankAccountsList) {
            if (bank.getId() == id) {
                int index = bankAccountsList.indexOf(bank);
                bankAccountsList.set(index, newBankAccounts);
            }
        }
        return "redirect:/ledger/financial";

    }
    @PostMapping("/ledger/bankAccounts/{id}/delete")
    public String deleteBankAccount(@PathVariable Long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<BankAccounts>  bankAccountsList = user.getBankAccounts();
        BankAccounts deletedBank = bankAccountsDao.getById(id);
        bankAccountsList.removeIf(insurancePolicy -> insurancePolicy.getId() == id);

        deletedBank.setUser(null);

        bankAccountsDao.deleteById(id);
        return "redirect:/ledger/financial";
    }

    //<----------------------------------------------------------- Credit Card Info ------------------------------------------------------------------->
    @GetMapping("/ledger/creditCard/{id}/edit")
    public String showEditCreditCard(@PathVariable long id, Model model) {
        CreditCard editCreditCard = creditCardDao.getById(id);
        model.addAttribute("editCreditCard", editCreditCard);
        return "ledger/credit_edit";

    }

    @PostMapping("/ledger/creditCard")
    public String saveCreditCardInformation(@ModelAttribute CreditCard newCreditCard){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        newCreditCard.setUser(user);
        List<CreditCard> creditCardList = user.getCreditCards();

        creditCardList.add(newCreditCard);

        creditCardDao.save(newCreditCard);

        return "redirect:/ledger/financial";
    }

    @PostMapping("/ledger/creditCard/{id}/edit")
    public String editCreditCardForm(@PathVariable long id,@RequestParam  String type,@RequestParam  String issuer, @ModelAttribute CreditCard editCreditCard){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<CreditCard> creditCardsList = user.getCreditCards();

        CreditCard newCreditCard = creditCardDao.getById(id);

        newCreditCard.setType(type);

        newCreditCard.setIssuer(issuer);

        newCreditCard.setUser(user);

        creditCardDao.save(newCreditCard);
        for (CreditCard creditCard : creditCardsList) {
            if (creditCard.getId() == id) {
                int index = creditCardsList.indexOf(creditCard);
                creditCardsList.set(index, newCreditCard);
            }
        }
        return "redirect:/ledger/financial";
    }

    @PostMapping("/ledger/creditCard/{id}/delete")
    public String deleteCreditCard(@PathVariable Long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<CreditCard> creditCardList = user.getCreditCards();
        CreditCard deletedCredit = creditCardDao.getById(id);
        creditCardList.removeIf(creditCard -> Objects.equals(creditCard.getId(), id));

        deletedCredit.setUser(null);

        creditCardDao.deleteById(id);
        return "redirect:/ledger/financial";
    }

}
