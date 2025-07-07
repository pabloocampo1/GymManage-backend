package com.GymManager.Backend.persistence.JpaServiceImpl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceResetPassword {

    @Autowired
    private JavaMailSender mailSender;

    public Boolean sendHtmlEmail(String to, String token) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            String subject = "Recuperación de contraseña - GymManager";
            String link = "http://localhost:5173/resetPassword?token=" + token;

            String htmlBody = "<html>" +
                    "<body style='font-family: Arial, sans-serif; text-align: center;'>" +
                    "<h2>Recuperación de Contraseña</h2>" +
                    "<p>Haz clic en el siguiente botón para restablecer tu contraseña:</p>" +
                    "<a href='" + link + "' style='background-color: #4CAF50; color: white; padding: 10px 20px; " +
                    "text-decoration: none; font-size: 16px; border-radius: 5px;'>Restablecer Contraseña</a>" +
                    "<p style='margin-top:20px;'>Este enlace expirará en 5 minutos.</p>" +
                    "</body>" +
                    "</html>";
            System.out.println(htmlBody);
            helper.setFrom("gymmman3@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);

            mailSender.send(message);
            System.out.println("se envio el email");
          return true;
        } catch (MessagingException e) {
           return false;
        }

    }
}
