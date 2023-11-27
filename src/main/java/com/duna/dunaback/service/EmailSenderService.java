package com.duna.dunaback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    //test
    public void sendEmail(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreplyduna@gmail.com");
        message.setTo("smgbysgm@gmail.com");
        message.setText("Test");
        message.setSubject("registration test");
        mailSender.send(message);
    }
}
