package com.GymManager.Backend.persistence.JpaRepositoriImpl;

import com.GymManager.Backend.domain.dto.SaleAndSuscription.SubscriptionDto;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SubscriptionResponse;
import com.GymManager.Backend.domain.repository.GymMemberPersistencePort;
import com.GymManager.Backend.domain.repository.MembresiaRepository;
import com.GymManager.Backend.domain.repository.SubscriptionPersistencePort;
import com.GymManager.Backend.persistence.Mappers.SaleAndSuscriptionMapper.SubscriptionMapper;
import com.GymManager.Backend.persistence.crudRepository.SubscriptionCrudRepository;
import com.GymManager.Backend.persistence.entity.GymMembers;
import com.GymManager.Backend.persistence.entity.MembershipEntity;
import com.GymManager.Backend.persistence.entity.SubscriptionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class SubscriptionJpaAdapter implements SubscriptionPersistencePort {
    private final SubscriptionCrudRepository subscriptionCrudRepository;
    private final GymMemberPersistencePort gymMemberPersistencePort;
    private final MembresiaRepository membresiaRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Autowired
    public SubscriptionJpaAdapter(SubscriptionCrudRepository subscriptionCrudRepository, GymMemberPersistencePort gymMemberPersistencePort, MembresiaRepository membresiaRepository, SubscriptionMapper subscriptionMapper) {
        this.subscriptionCrudRepository = subscriptionCrudRepository;
        this.gymMemberPersistencePort = gymMemberPersistencePort;
        this.membresiaRepository = membresiaRepository;
        this.subscriptionMapper = subscriptionMapper;
    }

    @Override
    public Boolean existById(Integer id) {
        return this.subscriptionCrudRepository.existsById(id);
    }

    @Override
    public List<SubscriptionEntity> findAll() {
        return (List<SubscriptionEntity>) this.subscriptionCrudRepository.findAll();
    }

    @Override
    public SubscriptionResponse save(SubscriptionDto dto) {
        GymMembers member = this.gymMemberPersistencePort.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Member en suscription save no found: " + dto.getUserId()));
        MembershipEntity membership = this.membresiaRepository.findById(dto.getMembershipId())
                .orElseThrow(() -> new IllegalArgumentException("Membership in suscription no found: " + dto.getMembershipId()));

        // si ese usuario no tiene una suscripcion se la creamos.
        if(!this.subscriptionCrudRepository.existsByMember_idMember(member.getIdMember())) {
            // terminar de agregar toda la logica.
            SubscriptionEntity newSubscription = new SubscriptionEntity();
            newSubscription.setMember(member);
            newSubscription.setMembership(membership);
            newSubscription.setStatus(true);
            newSubscription.setStartDate(LocalDateTime.now());
            newSubscription.setFinishDate(LocalDateTime.now().plusDays(membership.getDuration()));
            System.out.println("se creo una  suscripcion. ");
            SubscriptionEntity subscriptionSaved = this.subscriptionCrudRepository.save(newSubscription);
            return this.subscriptionMapper.suscriptionEntityToResponse(subscriptionSaved);
        }else{
           SubscriptionEntity subscription = this.subscriptionCrudRepository.findByMember_idMember(member.getIdMember())
                   .orElseThrow(() -> new IllegalArgumentException("Suscription not found."));
           subscription.setMembership(membership);
           subscription.setStartDate(LocalDateTime.now());
           subscription.setFinishDate(LocalDateTime.now().plusDays(membership.getDuration()));
           subscription.setStatus(true);
            System.out.println("se edito una  suscripcion. ");
            SubscriptionEntity subscriptionSaved = this.subscriptionCrudRepository.save(subscription);
           return this.subscriptionMapper.suscriptionEntityToResponse(subscriptionSaved);
        }

    }

    @Override
    public SubscriptionResponse getByUser(Integer userId) {
        return null;
    }

    @Override
    public boolean existsByMember_IdMember(Integer idMember) {
        return this.subscriptionCrudRepository.existsByMember_idMember(idMember);
    }

    @Override
    public Optional<SubscriptionEntity> findByMember_IdMember(Integer idMember) {
        return this.subscriptionCrudRepository.findByMember_idMember(idMember);
    }

    // esta funcion solo es para la actualizacion de las membresias en el schedule que actualiza las membresias.
    public void saveDirect(SubscriptionEntity subscription) {
        this.subscriptionCrudRepository.save(subscription);
    }
}
