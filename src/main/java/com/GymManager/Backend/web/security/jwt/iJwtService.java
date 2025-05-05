package com.GymManager.Backend.web.security.jwt;

import jakarta.validation.Valid;
import org.eclipse.angus.mail.imap.protocol.BODY;

public interface iJwtService {
    String createJwt(@Valid String username, @Valid String role);
    String getUser( @Valid String jwt);
    boolean isValidJwt(String jwt);
}
