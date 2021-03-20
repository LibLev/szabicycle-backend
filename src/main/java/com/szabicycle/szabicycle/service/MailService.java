package com.szabicycle.szabicycle.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class MailService {

    private JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(Map<String, String> data){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("szabicycle@gmail.com");
        mail.setFrom(data.get("emailAddress"));
        mail.setSubject(data.get("subject"));
        mail.setText(data.get("emailAddress")+"\n"+data.get("message"));
        javaMailSender.send(mail);
        SimpleMailMessage mail2 = new SimpleMailMessage();
        mail2.setTo(data.get("emailAddress"));
        mail2.setFrom(data.get("emailAddress"));
        mail2.setSubject("SZABICYCELNEK A KÖVET KEZŐ LEVELET KÜLDTED :"+data.get("subject"));
        mail2.setText("\""+data.get("message")+"\""+"\n\nHamarosan felvesszük veled a kapcsolatot." +
                "\n Addig is türelmedet kérjük!" +
                "\n\nÜdvözlettel" +
                "\nSzabiCycle");
        javaMailSender.send(mail2);
    }
}
