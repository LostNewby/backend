package com.naturalgoods.backend.auth.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class EmailService {
    @Value("${mail}")
    private String fromMail;

    @Autowired
    private JavaMailSender emailSender;


    public void sendNewPassword(String name, String password, String email) {
        String text = "Добрый день, " + name + "! \n" +
                "  \n" +
                "Ваш временный пароль: " + password + "\n";
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromMail);
            helper.setSubject("Временный пароля");
            helper.setTo(email);
            message.setText(text);
            emailSender.send(message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
