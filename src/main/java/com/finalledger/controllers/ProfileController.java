package com.finalledger.controllers;

import com.finalledger.models.*;
import com.finalledger.repositories.SiteContactRepository;
import com.finalledger.repositories.UserMedicalRepository;
import com.finalledger.repositories.UserPersonalRepository;
import com.finalledger.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Controller
public class ProfileController {

    private UserRepository userDao;
    private SiteContactRepository siteContactDao;
    private UserPersonalRepository userPersonalDao;
    private UserMedicalRepository userMedicalDao;
    private final PasswordEncoder passwordEncoder;

    @Value("${fileStackAPI}")
    public String fileStackAPIKey;

    public ProfileController(UserRepository userDao, SiteContactRepository siteContactDao, UserPersonalRepository userPersonalDao, UserMedicalRepository userMedicalDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.siteContactDao = siteContactDao;
        this.userPersonalDao = userPersonalDao;
        this.userMedicalDao = userMedicalDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User persistUser = userDao.getById(user.getId());

        List <User> userList = userDao.findAll();
        Collection<SiteContact> trustedUserList = siteContactDao.findContactsByOwner_userIs(persistUser.getId());
        System.out.println("Sanity test");
        System.out.println(trustedUserList);

        trustedUserList.forEach(contact ->{
            System.out.println("contact = " + contact.getAdded_user_id().getUsername());
        });

        model.addAttribute("messagingDisplay", false);
        model.addAttribute("message", new Message());
        model.addAttribute("user", persistUser);
        model.addAttribute("userList", userList);
        model.addAttribute("trustedUsers", trustedUserList);

        return "users/profile";
    }

    @GetMapping("/profile/{id}")
    public String goToSearchedProfile(@PathVariable long id, Model model){

        User user = userDao.getOne(id);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        boolean contactAlready = !siteContactDao.findByOwner_userAndAdded_user_idExists(id, currentUser.getId()).isEmpty();

        model.addAttribute("contactAlready", contactAlready);
        model.addAttribute("message", new Message());
        model.addAttribute("profileID", id);
        model.addAttribute("messagingDisplay", true);
        model.addAttribute("user", user);

        return "users/profile";

    }

    @GetMapping("/profile/settings")
    public String showProfileSettings(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User persistUser = userDao.getUserById(user.getId());
        model.addAttribute("user", persistUser);
        model.addAttribute("fileStackAPI", fileStackAPIKey);
//        model.addAttribute("showErrorMsg", false);
        return "users/profile_settings";
    }

    @GetMapping("/profile/settings?password")
    public String showProfileSettingsError(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User persistUser = userDao.getUserById(user.getId());
        model.addAttribute("user", persistUser);
        model.addAttribute("fileStackAPI", fileStackAPIKey);
        model.addAttribute("showErrorMsg", true);
        return "users/profile_settings";
    }

    @PostMapping("/profile/settings/username")
    public String updateUsername(@RequestParam(name = "username") String username) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User persistUser = userDao.getUserById(user.getId());
        persistUser.setUsername(username);
        userDao.save(persistUser);
        return "redirect:/profile/settings";
    }

    @PostMapping("/profile/settings")
    public String updatePassword(Model model, @RequestParam(name = "currentPswd") String currentPswd, @RequestParam(name = "newPswd") String newPswd) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User persistUser = userDao.getUserById(user.getId());
        String hash = passwordEncoder.encode(newPswd);
        if (!passwordEncoder.matches(currentPswd, persistUser.getPassword())) {
            model.addAttribute("user", persistUser);
            model.addAttribute("fileStackAPI", fileStackAPIKey);
            model.addAttribute("showErrorMsg", true);
            return "users/profile_settings";
        }
        persistUser.setPassword(hash);
        userDao.save(persistUser);
        return "redirect:/logout";
    }

    @PostMapping("/profile/settings/profile_pic")
    public String updateProfilePic(@RequestParam(name = "profilePicUrl") String profilePicUrl) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User persistUser = userDao.getUserById(user.getId());
        persistUser.setProfilePicUrl(profilePicUrl);
        userDao.save(persistUser);
        return "redirect:/profile/settings";
    }

    @PostMapping("/profile/settings/delete")
    public String deleteUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User persistUser = userDao.getById(user.getId());
        PersonalInformation userPersonalInfo = userPersonalDao.findByUserId(persistUser.getId());
        MedicalInformation userMedicalInfo = userMedicalDao.findByUserId(persistUser.getId());
        if (userPersonalInfo != null) {
            userPersonalDao.delete(userPersonalInfo);
        }
        if (userMedicalInfo != null) {
            userMedicalDao.delete(userMedicalInfo);
        }
        userDao.delete(persistUser);
        return "redirect:/logout";
    }

    @PostMapping("/profile/addcontact")
    public String addContact (@RequestParam(name="contactHidden") String addID){
        long incomingId = Long.parseLong(addID);

        SiteContact addedContact = new SiteContact();
        User addthisUserID = userDao.findById(incomingId);
        User contactlistOwner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        addedContact.setOwner_user(contactlistOwner);
        addedContact.setAdded_user_id(addthisUserID);
        siteContactDao.save(addedContact);

        return "redirect:/profile";

    }

    @GetMapping("/search")
    public String searchIndex(Model model, @RequestParam String searchedValue) {

//        PROFILE STUFF
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User persistUser = userDao.getById(user.getId());

        List <User> userList = userDao.findAll();
        Collection<SiteContact> trustedUserList = siteContactDao.findContactsByOwner_userIs(persistUser.getId());
        System.out.println("Sanity test");
        System.out.println(trustedUserList);

        trustedUserList.forEach(contact ->{
            System.out.println("contact = " + contact.getAdded_user_id().getUsername());
        });

        model.addAttribute("messagingDisplay", false);
        model.addAttribute("message", new Message());
        model.addAttribute("user", user);
        model.addAttribute("userList", userList);
        model.addAttribute("trustedUsers", trustedUserList);


//        SEARCH STUFF
        model.addAttribute("searchedValue", searchedValue.toLowerCase(Locale.ROOT));
        List <User> foundEmails = userDao.findAll();
        List <User> filteredList = new ArrayList<>();

        for (int i = 0; i < foundEmails.size(); i ++) {
            User userEmail = foundEmails.get(i);
            String email = userEmail.getEmail();

            if (email.toLowerCase().contains(searchedValue.toLowerCase())) {
                filteredList.add(userEmail);
            }
        }

        model.addAttribute("searchedEmails", filteredList);

        System.out.println("foundEmail = " + foundEmails);
        System.out.println("searchedValue = " + searchedValue);
        System.out.println("filterdList = " + filteredList);

        return "users/profile";

    }

}
