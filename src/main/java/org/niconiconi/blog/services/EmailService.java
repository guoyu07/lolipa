package org.niconiconi.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by Volio on 2016/11/5.
 */
@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final EnvService envService;

    @Autowired
    public EmailService(JavaMailSender mailSender, EnvService envService) {
        this.mailSender = mailSender;
        this.envService = envService;
    }

    public void sendText(String to, String title, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(envService.getMailFrom());
        message.setTo(to);
        message.setSubject(title);
        message.setText(text);
        mailSender.send(message);
    }
}
