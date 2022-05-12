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
    public String showUserMedicalForm(Model model, Principal principal) {

        model.addAttribute("medical", new MedicalInformation());

        return principal == null ? "redirect:/login" : "/ledger/medical";
    }

    @GetMapping("/ledger/medical/{id}/edit")
    public String editUserMedicalForm(@PathVariable long id, Model model, Principal principal){
        model.addAttribute("editMedical", userMedicalDao.getById(id));

        return principal == null ?  "redirect:/login" : "/ledger/medical/edit";
    }

    @PostMapping("/ledger/personal/{id}/edit")
    public String updatePersonal(@PathVariable Long id, @RequestParam String willLocation,@RequestParam String POADocLocation,@RequestParam String DNROrderLocation,@RequestParam String bloodType, String medicalConditions,@RequestParam String healthInsuranceName,@RequestParam User user){
        MedicalInformation medicalInformation = userMedicalDao.getById(id);
        medicalInformation.setWillLocation(willLocation);
        medicalInformation.setPOADocLocation(POADocLocation);
        medicalInformation.setDNROrderLocation(DNROrderLocation);
        medicalInformation.setBloodType(bloodType);
        medicalInformation.setMedicalConditions(medicalConditions);
        medicalInformation.setHealthInsuranceName(healthInsuranceName);
        medicalInformation.setUser(user);

        return "redirect:/ledger/medical";
    }

    @PostMapping("/ledger/medical/{id}/delete")
    public String deleteMedical(@PathVariable Long id){
        userMedicalDao.deleteById(id);

        return "redirect:/ledger/personal";
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
