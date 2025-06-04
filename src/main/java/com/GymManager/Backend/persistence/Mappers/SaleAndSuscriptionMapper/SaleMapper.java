package com.GymManager.Backend.persistence.Mappers.SaleAndSuscriptionMapper;

import com.GymManager.Backend.domain.dto.SaleAndSuscription.SaleResponse;
import com.GymManager.Backend.domain.repository.GymMemberPersistencePort;
import com.GymManager.Backend.domain.repository.MembresiaRepository;
import com.GymManager.Backend.persistence.entity.GymMembers;
import com.GymManager.Backend.persistence.entity.MembershipEntity;
import com.GymManager.Backend.persistence.entity.SaleRegisterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaleMapper {
    private final GymMemberPersistencePort gymMemberPersistencePort;
    private final MembresiaRepository membresiaRepository;

    @Autowired
    public SaleMapper(GymMemberPersistencePort gymMemberPersistencePort, MembresiaRepository membresiaRepository) {
        this.gymMemberPersistencePort = gymMemberPersistencePort;
        this.membresiaRepository = membresiaRepository;
    }


    public SaleResponse saleEntityToResponse(SaleRegisterEntity saleRegisterEntity){
        GymMembers gymMember = this.gymMemberPersistencePort.findById(saleRegisterEntity.getMember())
                .orElseThrow(() -> new IllegalArgumentException("user no found"));

        MembershipEntity membership = this.membresiaRepository.findById(saleRegisterEntity.getMembership())
                .orElseThrow(() -> new IllegalArgumentException("membership no found"));

        return SaleResponse
                .builder()
                .saleId(saleRegisterEntity.getId())
                .nameCustomer(gymMember.getFullName())
                .nameMembership(membership.getTitle())
                .amount(membership.getPrice())
                .paymentMethod(saleRegisterEntity.getPaymentMethod())
                .saleDate(saleRegisterEntity.getCreateDate())
                .ReceptionistName(saleRegisterEntity.getReceptionistName())
                .build();
    }



}
