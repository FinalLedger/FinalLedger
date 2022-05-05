package com.finalledger.controllers;
import com.finalledger.models.InsurancePolicy;
import com.finalledger.repositories.InsurancePolicyRepository;
import com.finalledger.repositories.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class InsurancePolicyController {

    private final UserRepository userDao;
    private final InsurancePolicyRepository insurancePolicyDao;

    public InsurancePolicyController(UserRepository userDao, InsurancePolicyRepository insurancePolicyDao) {
        this.userDao = userDao;
        this.insurancePolicyDao = insurancePolicyDao;
    }

    @GetMapping("/ledger/financial")
    public String showInsurancePolicyForm(Model model){
        model.addAttribute("insurancePolicy", new InsurancePolicy());
        return "/ledger/financial";
    }

    @PostMapping("/ledger/financial")
    public String saveInsurancePolicyInformation(@ModelAttribute InsurancePolicy insurancePolicy){


        return "redirect:/ledger/financial";
    }

}
