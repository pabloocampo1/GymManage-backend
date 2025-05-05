package com.GymManager.Backend.web.config;

import com.GymManager.Backend.domain.port.EmailPort;
import com.GymManager.Backend.persistence.JpaServiceImpl.EmailHomeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class ConfigEmail {

    // Registra EmailPort como un bean para que pueda ser inyectado en el controlador
    @Bean
    public EmailPort emailPort(JavaMailSender mailSender) {
        return new EmailHomeServiceImpl(mailSender);
    }
}
