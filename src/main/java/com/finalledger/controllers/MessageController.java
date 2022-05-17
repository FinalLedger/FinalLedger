package com.finalledger.controllers;

import com.finalledger.models.Message;
import com.finalledger.models.SiteContact;
import com.finalledger.models.User;
import com.finalledger.repositories.MessageRepository;
import com.finalledger.repositories.SiteContactRepository;
import com.finalledger.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MessageController {

    private MessageRepository messageDao;
    private UserRepository userDao;
    private SiteContactRepository siteContactDao;


    public MessageController(MessageRepository messageDao, UserRepository userDao, SiteContactRepository siteContactDao){
        this.messageDao = messageDao;
        this.userDao = userDao;
        this.siteContactDao = siteContactDao;

    }

    @GetMapping("/messages/send")
    public String sendMessage(Model model){

        model.addAttribute("message", new Message());
        return "draftmessage";
    }

//    @PostMapping("/messages/addcontact")
//    public String addContact (@RequestParam(name="contactHidden") long addID){
//
//        SiteContact addedContact = new SiteContact();
//        User addthisUserID = userDao.getOne(addID);
//        User contactlistOwner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        addedContact.setOwner_user(contactlistOwner);
//        addedContact.setAdded_user_id(addthisUserID);
//        siteContactDao.save(addedContact);
//
//        return "redirect:/profile";
//
//    }

    @PostMapping("/messages/send")
    public String sendMessage(@ModelAttribute Message message, @RequestParam(name="sendmessagetoID") long senttoID){
        User sentfromU = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User senttoU = userDao.getOne(senttoID);

        message.setReceiver_info(senttoU);
        message.setSender_info(sentfromU);
        messageDao.save(message);
        return "redirect:/messages";
    }

    @GetMapping("/messages")
    public String showMessages(Model model){

        User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Message> receivedMessages = messageDao.findAllByReceiver_info(u.getId());
        List<Message> sentMessages = messageDao.findAllBySender_info(u.getId());

        List<SiteContact> contactList = siteContactDao.findContactsByOwner_userIs(u.getId());

        if (contactList.size() == 0){
            model.addAttribute("hasContacts", false);
        } else {
            model.addAttribute("hasContacts", true);

        }

        if (receivedMessages.size() == 0 && sentMessages.size() == 0){
            model.addAttribute("messagingDisplay", false);
        } else {
            model.addAttribute("messagingDisplay", true);

        }

        if (receivedMessages.size() == 0){
            model.addAttribute("inboxDisplay", false);
        } else {
            model.addAttribute("inboxDisplay", true);
        }


        if (sentMessages.size() == 0){
            model.addAttribute("outboxDisplay", false);
        } else {
            model.addAttribute("outboxDisplay", true);
        }

        model.addAttribute("user", u);
        model.addAttribute("sentMessages", sentMessages);
        model.addAttribute("receivedMessages", receivedMessages);
        model.addAttribute("contactList", contactList);

        return "messages";
    }
}
