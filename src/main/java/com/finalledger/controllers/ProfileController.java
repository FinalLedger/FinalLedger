package com.finalledger.controllers;

import com.finalledger.models.Message;
import com.finalledger.models.SiteContact;
import com.finalledger.models.User;
import com.finalledger.repositories.SiteContactRepository;
import com.finalledger.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

@Controller
public class ProfileController {

    private UserRepository userDao;
    private SiteContactRepository siteContactDao;

    public ProfileController(UserRepository userDao, SiteContactRepository siteContactDao) {
        this.userDao = userDao;
        this.siteContactDao = siteContactDao;
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User persistUser = userDao.getById(user.getId());

        String mainUserMessage;
        if (user.isMainUser()){
            mainUserMessage = "Main User";
        } else {
            mainUserMessage = "Basic User";
        }

        List <User> userList = userDao.findAll();
        Collection<SiteContact> trustedUserList = siteContactDao.findContactsByOwner_userIs(persistUser.getId());

        System.out.println("Sanity test");
        System.out.println(trustedUserList);

        trustedUserList.forEach(contact ->{
            System.out.println("contact = " + contact.getAdded_user_id().getUsername());
        });

        model.addAttribute("messagingDisplay", false);
        model.addAttribute("message", new Message());
        model.addAttribute("mainUserMessage", mainUserMessage);
        model.addAttribute("user", user);
        model.addAttribute("userList", userList);
        model.addAttribute("trustedUsers", trustedUserList);

        return "users/profile";
    }

    @GetMapping("/profile/{id}")
    public String goToSearchedProfile(@PathVariable long id, Model model){

        User user = userDao.getOne(id);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String mainUserMessage;
        if (user.isMainUser()){
            mainUserMessage = "Main User";
        } else {
            mainUserMessage = "Guest";
        }

        boolean contactAlready = !siteContactDao.findByOwner_userAndAdded_user_idExists(id, currentUser.getId()).isEmpty();

        model.addAttribute("contactAlready", contactAlready);
        model.addAttribute("message", new Message());
        model.addAttribute("profileID", id);
        model.addAttribute("messagingDisplay", true);
        model.addAttribute("mentorMessage", mainUserMessage);
        model.addAttribute("user", user);

        return "users/profile";

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
}
