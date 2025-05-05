package com.GymManager.Backend.persistence.JpaServiceImpl;

import com.GymManager.Backend.domain.dto.EMailHomeDto;
import com.GymManager.Backend.domain.port.EmailPort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailHomeServiceImpl implements EmailPort {
    private final JavaMailSender mailSender;
    private static final String DESTINATION_EMAIL = "marinsantiagosmmg@gmail.com";

    public EmailHomeServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendContactEmail(EMailHomeDto eMailHomeDto) {
        String subject = "Nuevo mensaje de contacto";
        String body = String.format(
                "La persona  \"%s\" con el correo   \"%s\" quiere comunicarte que:\n\n%s",
                eMailHomeDto.getName(),
                eMailHomeDto.getEmail(),
                eMailHomeDto.getMessage()
        );

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("pruebamg12345@gmail.com");
        message.setTo(DESTINATION_EMAIL);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
