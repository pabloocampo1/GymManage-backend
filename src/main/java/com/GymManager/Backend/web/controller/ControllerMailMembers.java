package com.GymManager.Backend.web.controller;

import com.GymManager.Backend.domain.dto.EMailHomeDto;
import com.GymManager.Backend.domain.dto.EmailMembersDto;
import com.GymManager.Backend.domain.port.EmailPort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/correo")
public class ControllerMailMembers {


        private final EmailPort emailPort;

        public ControllerMailMembers(EmailPort emailPort) {
            this.emailPort = emailPort;
        }

        @PostMapping("/send")
        public String sendContactMessage(@RequestBody EmailMembersDto emailMembersDto) {
            System.out.println("Recibido en el controlador:");
            System.out.println("Asunto: " + emailMembersDto.getAsunt());
            System.out.println("Contenido: " + emailMembersDto.getContenido());
            System.out.println("Estado: " + emailMembersDto.getEstado());
            emailPort.sendEmailMembers(emailMembersDto);
            return "Tu mensaje fue enviado exitosamente.";
        }

}
