package com.GymManager.Backend.web.controller;

import com.GymManager.Backend.domain.dto.EMailHomeDto;
import com.GymManager.Backend.domain.port.EmailPort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact")
public class ControllerEmailHome {
    private final EmailPort emailPort;

    public ControllerEmailHome(EmailPort emailPort) {
        this.emailPort = emailPort;
    }

    @PostMapping("/send")
    public String sendContactMessage(@RequestBody EMailHomeDto eMailHomeDto) {
        emailPort.sendContactEmail(eMailHomeDto);
        return "Tu mensaje fue enviado exitosamente.";
    }
}
