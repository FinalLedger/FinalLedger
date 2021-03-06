package com.finalledger.controllers;

import com.finalledger.models.User;
import com.finalledger.models.PersonalInformation;
import com.finalledger.repositories.UserPersonalRepository;
import com.finalledger.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class UserPersonalController {

    private final UserRepository userDao;
    private final UserPersonalRepository userPersonalDao;

    public UserPersonalController(UserRepository userDao, UserPersonalRepository userPersonalDao) {
        this.userDao = userDao;
        this.userPersonalDao = userPersonalDao;
    }

    @GetMapping("/ledger/personal")
    public String showUserPersonalForm(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) {
            return "redirect:/login";
        }
        PersonalInformation personalInfo = userPersonalDao.findByUserId(user.getId());
        if (personalInfo == null) {
            model.addAttribute("existingInfo", false);
            model.addAttribute("personalInfo", new PersonalInformation());
        } else {
            model.addAttribute("existingInfo", true);
            model.addAttribute("personalInfo", personalInfo);
        }
        model.addAttribute("isGuestUser", false);
        return "ledger/personal";
    }

    @GetMapping("/ledger/{id}/personal")
    public String showConnectionPersonal(@PathVariable long id, Model model) {
        User mainUser = userDao.getUserById(id);
        PersonalInformation personalInfo = userPersonalDao.findByUserId(id);
        if (personalInfo == null) {
            model.addAttribute("existingInfo", false);
        } else {
            model.addAttribute("existingInfo", true);
            model.addAttribute("personalInfo", personalInfo);
        }
        model.addAttribute("mainUserId", id);
        model.addAttribute("mainUserName", mainUser.getUsername());
        model.addAttribute("isGuestUser", true);
        return "ledger/personal";
    }

    @PostMapping("/ledger/personal/{id}/edit")
    public String updatePersonal(@PathVariable Long id, @RequestParam String legalName, @RequestParam String maidenName, @RequestParam String primaryAddress, @RequestParam String phoneNumber, @RequestParam String birthPlace, @RequestParam String maritalStatus, @RequestParam String occupation, @RequestParam String citizenship, @RequestParam String religion, @RequestParam String militaryStatus) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        PersonalInformation personalInfo = userPersonalDao.getById(id);

        personalInfo.setLegalName(legalName);

        personalInfo.setMaidenName(maidenName);

        personalInfo.setPrimaryAddress(primaryAddress);

        personalInfo.setPhoneNumber(phoneNumber);

        personalInfo.setBirthPlace(birthPlace);

        personalInfo.setMaritalStatus(maritalStatus);

        personalInfo.setOccupation(occupation);

        personalInfo.setCitizenship(citizenship);

        personalInfo.setReligion(religion);

        personalInfo.setMilitaryStatus(militaryStatus);

        personalInfo.setUser(user);

        userPersonalDao.save(personalInfo);

        return "redirect:/ledger/personal";
    }

    @PostMapping("/ledger/personal/{id}/delete")
    public String deletePersonal(@PathVariable Long id) {
        userPersonalDao.deleteById(id);

        return "redirect:/ledger/personal";
    }

    @PostMapping("/ledger/personal")
    public String saveUserPersonalInformation(@ModelAttribute PersonalInformation userPersonalInformation) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User persistUser = userDao.getById(user.getId());
        userPersonalInformation.setUser(persistUser);

        PersonalInformation existingInfoUser = userPersonalDao.findByUserId(persistUser.getId());

        if (existingInfoUser != null) {

            return "redirect:/ledger/personal";

        }

        ArrayList<PersonalInformation> document = new ArrayList<>();
        document.add(userPersonalInformation);
        userDao.save(persistUser);

        userPersonalDao.save(userPersonalInformation);

        return "redirect:/ledger/personal";
    }
}
