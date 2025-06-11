package com.GymManager.Backend.domain.port;

import com.GymManager.Backend.domain.dto.EMailHomeDto;
import com.GymManager.Backend.domain.dto.EmailMembersDto;

public interface EmailPort {
    void sendContactEmail(EMailHomeDto eMailHomeDto);

    //yo puedo agg otro aca?:
    void sendEmailMembers(EmailMembersDto emailMembersDto);
}
