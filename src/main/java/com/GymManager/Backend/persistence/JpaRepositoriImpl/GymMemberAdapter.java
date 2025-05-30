package com.GymManager.Backend.persistence.JpaRepositoriImpl;


import com.GymManager.Backend.domain.repository.GymMemberPersistencePort;
import com.GymManager.Backend.domain.repository.SubscriptionPersistencePort;
import com.GymManager.Backend.persistence.crudRepository.GymMemberCrudRepo;
import com.GymManager.Backend.persistence.entity.GymMembers;
import com.GymManager.Backend.persistence.entity.SubscriptionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository

public class GymMemberAdapter implements GymMemberPersistencePort {
    private final GymMemberCrudRepo gymMemberCrudRepo;
    private final SubscriptionPersistencePort subscriptionPersistencePort;

    @Autowired
    public GymMemberAdapter(GymMemberCrudRepo gymMemberCrudRepo, @Lazy SubscriptionPersistencePort subscriptionPersistencePort) {
        this.gymMemberCrudRepo = gymMemberCrudRepo;
        this.subscriptionPersistencePort = subscriptionPersistencePort;
    }


    @Override
    public GymMembers save(GymMembers gymMember) {
        return gymMemberCrudRepo.save(gymMember);
    }

    @Override
    public Optional<GymMembers> findById(Integer id) {
        return gymMemberCrudRepo.findById(id);
    }

    @Override
    public Optional<GymMembers> findByIdentificationNumber(Integer id) {
        return this.gymMemberCrudRepo.findByIdentificationNumber(id);
    }

    @Override
    public List<GymMembers> findAll() {
        return (List<GymMembers>) gymMemberCrudRepo.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        this.subscriptionPersistencePort.findByMember_IdMember(id).ifPresent(this.subscriptionPersistencePort::delete);
        gymMemberCrudRepo.deleteById(id);
    }

    @Override
    public GymMembers update(GymMembers gymMember) {
        return gymMemberCrudRepo.save(gymMember);
    }

    @Override
    public Boolean existById(Integer id) {
        return this.gymMemberCrudRepo.existsById(id);
    }

    @Override
    public Boolean existByIdentification(Integer id) {
        return this.gymMemberCrudRepo.existsByIdentificationNumber(id);
    }
}
