package com.finalledger.controllers;
import com.finalledger.models.CreditCard;
import com.finalledger.repositories.CreditCardRepository;
import com.finalledger.repositories.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class CreditCardController {
    private final UserRepository userDao;
    private final CreditCardRepository creditCardDao;
    public CreditCardController(UserRepository userDao, CreditCardRepository creditCardDao) {
        this.userDao = userDao;
        this.creditCardDao = creditCardDao;
    }

    @GetMapping("/ledger/financial")
    public String showCreditCardForm(Model model){
        model.addAttribute("creditCard", new CreditCard());
        return "/ledger/financial";
    }

    @PostMapping("/ledger/financial")
    public String saveCreditCardInformation(@ModelAttribute CreditCard creditCard){

        return "redirect:/ledger/financial";
    }
}
