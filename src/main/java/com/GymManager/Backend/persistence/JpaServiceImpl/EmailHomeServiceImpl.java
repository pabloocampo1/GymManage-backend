package com.GymManager.Backend.persistence.JpaServiceImpl;

import com.GymManager.Backend.domain.dto.EMailHomeDto;
import com.GymManager.Backend.domain.dto.EmailMembersDto;
import com.GymManager.Backend.domain.port.EmailPort;
import com.GymManager.Backend.domain.repository.GymMemberPersistencePort;
import com.GymManager.Backend.domain.repository.SubscriptionPersistencePort;
import com.GymManager.Backend.persistence.entity.GymMembers;
import com.GymManager.Backend.persistence.entity.SubscriptionEntity;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailHomeServiceImpl implements EmailPort {
    private final JavaMailSender mailSender;
    private final SubscriptionPersistencePort subscriptionRepository;
    private final GymMemberPersistencePort gymMembersRepository;
    private static final String DESTINATION_EMAIL = "marinsantiagosmmg@gmail.com";

    public EmailHomeServiceImpl(JavaMailSender mailSender,
                                SubscriptionPersistencePort subscriptionRepository,
                                GymMemberPersistencePort gymMembersRepository) {
        this.mailSender = mailSender;
        this.subscriptionRepository = subscriptionRepository;
        this.gymMembersRepository = gymMembersRepository;
    }

    @Override
    public void sendContactEmail(EMailHomeDto eMailHomeDto) {
        String subject = "Nuevo mensaje de contacto";

        String htmlBody = String.format(
                "<html>" +
                        "<body style='font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;'>" +
                        "<div style='background-color: #ffffff; padding: 30px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);'>" +
                        "<h2 style='color: #333333;'>¡Tienes un nuevo mensaje!</h2>" +
                        "<p style='color: #666666;'>La persona <strong>%s</strong> con el correo <strong>%s</strong> quiere comunicarte lo siguiente:</p>" +
                        "<div style='background-color: #e9e9e9; padding: 15px; border-left: 5px solid #007bff; margin-top: 20px;'>" +
                        "<p style='color: #333333; margin: 0;'>%s</p>" +
                        "</div>" +
                        "<img src='/Descargas/carrusel4' alt='Gimnasio' style='max-width: 150px; margin-top: 20px;'/>" +
                        "</div>" +
                        "</body>" +
                        "</html>",
                eMailHomeDto.getName(),
                eMailHomeDto.getEmail(),
                eMailHomeDto.getMessage()
        );

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("pruebamg12345@gmail.com");
            helper.setTo(DESTINATION_EMAIL);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Error al enviar el correo HTML: " + e.getMessage());
        }
    }

    @Override
    public void sendEmailMembers(EmailMembersDto emailMembersDto) {
        List<SubscriptionEntity> subscriptions;

        // Manejar los tres estados: 0 = inactivos, 1 = activos, 2 = todos
        if (emailMembersDto.getEstado() == 2) {
            subscriptions = subscriptionRepository.findAll();
        } else {
            boolean status = emailMembersDto.getEstado() == 1;
            subscriptions = subscriptionRepository.findByStatus(status);
        }

        // Obtener miembros desde las suscripciones
        List<GymMembers> members = subscriptions.stream()
                .map(SubscriptionEntity::getMember)
                .toList();

        // Enviar email a cada miembro
        for (GymMembers member : members) {
            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

                helper.setFrom("pruebamg12345@gmail.com");
                helper.setTo(member.getEmail());
                helper.setSubject(emailMembersDto.getAsunt());

                String htmlBody = String.format(
                        "<html>" +
                                "<body style='font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;'>" +
                                "<div style='background-color: #ffffff; padding: 30px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);'>" +
                                "<h2 style='color: #333333;'>Hola %s</h2>" +
                                "<div style='background-color: #e9e9e9; padding: 15px; border-left: 5px solid #007bff; margin-top: 20px;'>" +
                                "<p style='color: #333333; margin: 0;'>%s</p>" +
                                "</div>" +
                                "</div>" +
                                "</body>" +
                                "</html>",
                        member.getFullName(),
                        emailMembersDto.getContenido()
                );

                helper.setText(htmlBody, true);
                mailSender.send(message);

            } catch (MessagingException e) {
                e.printStackTrace();
                System.err.println("Error al enviar el correo a " + member.getEmail() + ": " + e.getMessage());
            }
        }
    }
}
