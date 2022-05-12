package com.finalledger.controllers;

import com.finalledger.models.Address;
import com.finalledger.models.User;
import com.finalledger.models.PersonalInformation;
import com.finalledger.repositories.UserPersonalRepository;
import com.finalledger.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserPersonalController {

    private final UserRepository userDao;
    private final UserPersonalRepository userPersonalDao;

    public UserPersonalController(UserRepository userDao, UserPersonalRepository userPersonalDao) {
        this.userDao = userDao;
        this.userPersonalDao = userPersonalDao;
    }

    @GetMapping("/ledger/personal")
    public String showUserPersonalForm(Model model, Principal principal) {

        model.addAttribute("userPersonalInformation", new PersonalInformation());

        return principal == null ?  "redirect:/login" : "/ledger/personal";
    }

    @GetMapping("/ledger/personal/{id}/edit")
    public String editUserPersonalForm(@PathVariable long id,Model model, Principal principal){
        model.addAttribute("editPersonal", userPersonalDao.getById(id));

        return principal == null ?  "redirect:/login" : "/ledger/personal/edit";
    }

    @PostMapping("/ledger/personal/{id}/edit")
    public String updatePersonal(@PathVariable Long id,@RequestParam String legalName,@RequestParam String maidenName,@RequestParam String primaryAddress, @RequestParam String phoneNumber,@RequestParam String birthPlace,@RequestParam String maritalStatus,@RequestParam String occupation,@RequestParam String citizenship,@RequestParam String religion,@RequestParam String militaryStatus,@RequestParam User user){
        PersonalInformation personalInformation = userPersonalDao.getById(id);

        personalInformation.setLegalName(legalName);

        personalInformation.setMaidenName(maidenName);

        personalInformation.setPrimaryAddress(primaryAddress);

        personalInformation.setPhoneNumber(phoneNumber);

        personalInformation.setBirthPlace(birthPlace);

        personalInformation.setMaritalStatus(maritalStatus);

        personalInformation.setOccupation(occupation);

        personalInformation.setCitizenship(citizenship);

        personalInformation.setReligion(religion);

        personalInformation.setMilitaryStatus(militaryStatus);

        personalInformation.setUser(user);


        return "redirect:/ledger/personal";
    }
    @PostMapping("/ledger/personal/{id}/delete")
    public String deletePersonal(@PathVariable Long id){
        userPersonalDao.deleteById(id);

        return "redirect:/ledger/personal";
    }


    @PostMapping("/ledger/personal")
    public String saveUserPersonalInformation(@ModelAttribute PersonalInformation userPersonalInformation){

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
