package com.sanvui.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * @author: VuiSK
 * @created: 04/12/2021-8:13 AM
 * @mailto: sanvankhanh@gmail.com
 */
@Configuration
public class MailConfig {

    @Autowired
    MailPropertie mailPropertie;

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername(mailPropertie.getSenderEmail());
        javaMailSender.setPassword(mailPropertie.getPassword());

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.starttls.enable", "true");
        javaMailSender.setJavaMailProperties(properties);

        return javaMailSender;
    }
}
