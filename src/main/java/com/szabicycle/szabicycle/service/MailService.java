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
        log.info(data.toString());
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("szabicycle@gmail.com");
        mail.setFrom(data.get("emailAddress"));
        mail.setSubject(data.get("subject"));
        mail.setText(data.get("emailAddress")+"\n"+data.get("message"));
        javaMailSender.send(mail);
    }
}
