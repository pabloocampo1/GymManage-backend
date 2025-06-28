package com.GymManager.Backend.web.config;

import com.GymManager.Backend.domain.port.EmailPort;
import com.GymManager.Backend.domain.repository.GymMemberPersistencePort;
import com.GymManager.Backend.domain.repository.SubscriptionPersistencePort;
import com.GymManager.Backend.persistence.JpaServiceImpl.EmailHomeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class ConfigEmail {

    // Registra EmailPort como un bean para que pueda ser inyectado en el controlador
    @Bean
    public EmailPort emailPort(
            JavaMailSender mailSender,
            SubscriptionPersistencePort subscriptionPersistencePort,
            GymMemberPersistencePort gymMemberPersistencePort // ¡Añadir este argumento!
    ) {
        // Ahora pasamos todos los tres argumentos que el constructor de EmailHomeServiceImpl espera
        return new EmailHomeServiceImpl(mailSender, subscriptionPersistencePort, gymMemberPersistencePort);
    }
}
