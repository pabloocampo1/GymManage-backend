package com.GymManager.Backend.persistence.JpaRepositoriImpl;

import com.GymManager.Backend.domain.dto.SaleAndSuscription.SaleDto;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SaleResponse;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SubscriptionDto;
import com.GymManager.Backend.domain.repository.GymMemberPersistencePort;
import com.GymManager.Backend.domain.repository.SalePersitencePort;
import com.GymManager.Backend.domain.repository.SubscriptionPersistencePort;
import com.GymManager.Backend.persistence.Mappers.SaleAndSuscriptionMapper.SaleMapper;
import com.GymManager.Backend.persistence.crudRepository.SaleCrudRepository;
import com.GymManager.Backend.persistence.entity.GymMembers;
import com.GymManager.Backend.persistence.entity.MembershipEntity;
import com.GymManager.Backend.persistence.entity.SaleRegisterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SaleJpaAdapter implements SalePersitencePort {
    private final SaleCrudRepository saleCrudRepository;
    private final GymMemberPersistencePort gymMemberPersistencePort;
    private final SaleMapper saleMapper;
    private final SubscriptionPersistencePort subscriptionPersistencePort;

    @Autowired
    public SaleJpaAdapter(SaleCrudRepository saleCrudRepository, GymMemberPersistencePort gymMemberPersistencePort, SaleMapper saleMapper, SubscriptionPersistencePort subscriptionPersistencePort) {
        this.saleCrudRepository = saleCrudRepository;
        this.gymMemberPersistencePort = gymMemberPersistencePort;
        this.saleMapper = saleMapper;
        this.subscriptionPersistencePort = subscriptionPersistencePort;
    }

    @Override
    public SaleResponse save(SaleDto saleDto, GymMembers gymMembers, MembershipEntity membership) {
        SaleRegisterEntity saleRegisterEntity = new SaleRegisterEntity();
        saleRegisterEntity.setMember(gymMembers.getIdMember());
        saleRegisterEntity.setAmount(membership.getPrice());
        saleRegisterEntity.setMembership(membership.getId());
        saleRegisterEntity.setPaymentMethod(saleDto.getPurchaseMethod());
        saleRegisterEntity.setReceptionistName(saleDto.getReceptionistName());

        SubscriptionDto subscriptionDto = new SubscriptionDto( membership.getId(), gymMembers.getIdMember());
        this.subscriptionPersistencePort.save(subscriptionDto);
        return this.saleMapper.saleEntityToResponse(this.saleCrudRepository.save(saleRegisterEntity));

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<SaleResponse> getAll() {
        return List.of();
    }

    @Override
    public Boolean existById(Integer id) {
        return null;
    }


}
