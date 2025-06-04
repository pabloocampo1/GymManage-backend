package com.GymManager.Backend.persistence.JpaServiceImpl;

import com.GymManager.Backend.domain.dto.SaleAndSuscription.SubscriptionDto;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SubscriptionResponse;
import com.GymManager.Backend.domain.repository.GymMemberPersistencePort;
import com.GymManager.Backend.domain.repository.MembresiaRepository;
import com.GymManager.Backend.domain.repository.SubscriptionPersistencePort;
import com.GymManager.Backend.domain.service.SubscriptionService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionPersistencePort subscriptionPersistencePort;
    private final GymMemberPersistencePort gymMemberPersistencePort;
    private final MembresiaRepository membresiaRepository;
    public SubscriptionServiceImpl(SubscriptionPersistencePort subscriptionPersistencePort, GymMemberPersistencePort gymMemberPersistencePort, MembresiaRepository membresiaRepository) {
        this.subscriptionPersistencePort = subscriptionPersistencePort;
        this.gymMemberPersistencePort = gymMemberPersistencePort;
        this.membresiaRepository = membresiaRepository;
    }

    @Override
    @Transactional
    public SubscriptionResponse save(@Valid SubscriptionDto subscriptionDto) {
        try{
            if(this.gymMemberPersistencePort.existById(subscriptionDto.getUserId())) {
                throw new IllegalArgumentException("User not found :  " + subscriptionDto.getUserId());
            }
            if(this.membresiaRepository.existById(subscriptionDto.getMembershipId())) {
                throw new IllegalArgumentException("Membership not found :  " + subscriptionDto.getMembershipId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return this.subscriptionPersistencePort.save(subscriptionDto);
    }

    @Override
    public SubscriptionResponse getByUser(Integer userId) {
        return this.subscriptionPersistencePort.getByUser(userId);
    }
}
