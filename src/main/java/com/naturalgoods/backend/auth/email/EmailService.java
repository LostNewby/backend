package com.naturalgoods.backend.auth.email;

import com.naturalgoods.backend.auth.Language;
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
    @Value("${mail.username}")
    private String fromMail;

    @Autowired
    private JavaMailSender emailSender;


    public void sendNewPassword(String name, String password, String email, Language lang) {
        String text="";
        if(lang.equals(Language.RU)){
            text = "Добрый день, " + name + "! \n \n" +
                    "Ваш временный пароль: " + password + "\n";
        }else if(lang.equals(Language.KK)){
            text = "Қайырлы күн, " + name + "! \n \n" +
                    "Сіздің уақытша құпия сөзіңіз: " + password + "\n";
        }else if(lang.equals(Language.EN)){
            text = "Good afternoon, " + name + "! \n \n" +
                    "Your temporary password: " + password + "\n";
        }
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromMail);
            if(lang.equals(Language.RU)){
                helper.setSubject("Пароль");
            }else if(lang.equals(Language.KK)){
                helper.setSubject("Құпия сөз");
            }else if(lang.equals(Language.EN)){
                helper.setSubject("Password");
            }
            helper.setTo(email);
            message.setText(text);
            emailSender.send(message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public void sendBanNotice(String name, String email) {
        String text = "Добрый день, " + name + "! \n" +
                "  \n" +
                "Вы были добавлены в черный список AulFood!\n";
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromMail);
            helper.setSubject("Черный список");
            helper.setTo(email);
            message.setText(text);
            emailSender.send(message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
