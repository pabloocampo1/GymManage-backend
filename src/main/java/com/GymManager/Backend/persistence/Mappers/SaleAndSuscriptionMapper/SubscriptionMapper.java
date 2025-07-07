package com.GymManager.Backend.persistence.Mappers.SaleAndSuscriptionMapper;

import com.GymManager.Backend.domain.dto.SaleAndSuscription.SubscriptionResponse;
import com.GymManager.Backend.persistence.entity.SubscriptionEntity;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMapper {

    public SubscriptionResponse suscriptionEntityToResponse(SubscriptionEntity suscription) {
        return SubscriptionResponse
                .builder()
                .userId(suscription.getMember().getIdMember().toString())
                .username(suscription.getMember().getFullName())
                .DNI(suscription.getMember().getIdentificationNumber().toString())
                .MembershipStatus(suscription.getStatus())
                .membershipName(suscription.getMembership().getTitle())
                .build();
    }



}
