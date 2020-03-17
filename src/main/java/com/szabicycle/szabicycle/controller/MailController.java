package com.szabicycle.szabicycle.controller;

import com.szabicycle.szabicycle.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin
public class MailController {

    @Autowired
    MailService mailService;

    @PostMapping("/send-mail")
    public void sendMail(@RequestBody Map<String, String> data){
        mailService.sendMail(data);
    }
}
