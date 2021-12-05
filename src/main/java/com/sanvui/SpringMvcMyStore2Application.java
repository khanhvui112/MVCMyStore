package com.sanvui;

import com.sanvui.model.dto.param.MailParamDto;
import com.sanvui.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@EnableJpaAuditing
@SpringBootApplication
public class SpringMvcMyStore2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcMyStore2Application.class, args);
    }

}
