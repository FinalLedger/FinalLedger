package com.finalledger.controllers;

import com.finalledger.models.Address;
import com.finalledger.models.PersonalInformation;
import com.finalledger.models.User;
import com.finalledger.models.MedicalInformation;
import com.finalledger.repositories.UserMedicalRepository;
import com.finalledger.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserMedicalController {
    private final UserRepository userDao;
    private final UserMedicalRepository userMedicalDao;

    public UserMedicalController(UserRepository userDao, UserMedicalRepository userMedicalDao) {
        this.userDao = userDao;
        this.userMedicalDao = userMedicalDao;
    }

    @GetMapping("/ledger/medical")
    public String showUserMedicalForm(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) {
            return "redirect:/login";
        }
        MedicalInformation medicalInfo = userMedicalDao.findByUserId(user.getId());
        if (medicalInfo == null) {
            model.addAttribute("existingInfo", false);
            model.addAttribute("medicalInfo", new MedicalInformation());
        } else {
            model.addAttribute("existingInfo", true);
            model.addAttribute("medicalInfo", medicalInfo);
        }

        return "ledger/medical";
    }


    @PostMapping("/ledger/medical/{id}/edit")
    public String updateMedical(@PathVariable Long id, @RequestParam String willLocation,@RequestParam String POADocLocation,@RequestParam String DNROrderLocation,@RequestParam String bloodType, String medicalConditions,@RequestParam String healthInsuranceName){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        MedicalInformation medicalInfo = userMedicalDao.getById(id);

        medicalInfo.setWillLocation(willLocation);

        medicalInfo.setPOADocLocation(POADocLocation);

        medicalInfo.setDNROrderLocation(DNROrderLocation);

        medicalInfo.setBloodType(bloodType);

        medicalInfo.setMedicalConditions(medicalConditions);

        medicalInfo.setHealthInsuranceName(healthInsuranceName);

        medicalInfo.setUser(user);

        userMedicalDao.save(medicalInfo);

        return "redirect:/ledger/medical";
    }

    @PostMapping("/ledger/medical/{id}/delete")
    public String deleteMedical(@PathVariable Long id){
        userMedicalDao.deleteById(id);

        return "redirect:/ledger/medical";
    }

    @PostMapping("/ledger/medical")
    public String saveMedicalInformation(@ModelAttribute MedicalInformation userMedicalInformation) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User persistUser = userDao.getById(user.getId());
        userMedicalInformation.setUser(persistUser);

        MedicalInformation existingInfoUser = userMedicalDao.findByUserId(persistUser.getId());

        if (existingInfoUser != null) {

            return "redirect:/ledger/medical";
        }

        ArrayList<MedicalInformation> document = new ArrayList<>();
        document.add(userMedicalInformation);
        userDao.save(persistUser);

        userMedicalDao.save(userMedicalInformation);

        return"redirect:/ledger/medical";
    }

}
