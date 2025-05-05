package com.GymManager.Backend.domain.port;

import com.GymManager.Backend.domain.dto.EMailHomeDto;

public interface EmailPort {
    void sendContactEmail(EMailHomeDto eMailHomeDto);
}
