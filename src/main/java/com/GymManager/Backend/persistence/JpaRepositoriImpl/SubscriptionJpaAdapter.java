package com.GymManager.Backend.persistence.JpaRepositoriImpl;

import com.GymManager.Backend.domain.dto.DashboardDtos.MostUsedActiveMembershipDto;
import com.GymManager.Backend.domain.dto.DashboardDtos.TotalActiveAndInactiveMembers;
import com.GymManager.Backend.domain.dto.DashboardDtos.TotalVisitAccessesPerMonth;
import com.GymManager.Backend.domain.dto.DashboardDtos.UserTypeloggedInDto;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SubscriptionDto;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SubscriptionResponse;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SubscriptionStatus;
import com.GymManager.Backend.domain.repository.GymMemberPersistencePort;
import com.GymManager.Backend.domain.repository.MembresiaRepository;
import com.GymManager.Backend.domain.repository.SubscriptionPersistencePort;
import com.GymManager.Backend.domain.repository.VisitsPersistencePort;
import com.GymManager.Backend.persistence.Mappers.SaleAndSuscriptionMapper.SubscriptionMapper;
import com.GymManager.Backend.persistence.crudRepository.SubscriptionCrudRepository;
import com.GymManager.Backend.persistence.entity.GymMembers;
import com.GymManager.Backend.persistence.entity.MembershipEntity;
import com.GymManager.Backend.persistence.entity.SubscriptionEntity;
import com.GymManager.Backend.persistence.projections.ActiveInactiveCount;
import com.GymManager.Backend.persistence.projections.SubscriptionStatusView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SubscriptionJpaAdapter implements SubscriptionPersistencePort {
    private final SubscriptionCrudRepository subscriptionCrudRepository;
    private final GymMemberPersistencePort gymMemberPersistencePort;
    private final MembresiaRepository membresiaRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final VisitsPersistencePort visitsPersistencePort;

    @Autowired
    public SubscriptionJpaAdapter(SubscriptionCrudRepository subscriptionCrudRepository, GymMemberPersistencePort gymMemberPersistencePort, MembresiaRepository membresiaRepository, SubscriptionMapper subscriptionMapper, VisitsPersistencePort visitsPersistencePort) {
        this.subscriptionCrudRepository = subscriptionCrudRepository;
        this.gymMemberPersistencePort = gymMemberPersistencePort;
        this.membresiaRepository = membresiaRepository;
        this.subscriptionMapper = subscriptionMapper;
        this.visitsPersistencePort = visitsPersistencePort;
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
        if(!this.subscriptionCrudRepository.existsByMember_idMember(member.getIdMember())) {
            SubscriptionEntity newSubscription = new SubscriptionEntity();
            newSubscription.setMember(member);
            newSubscription.setMembership(membership);
            newSubscription.setStatus(true);
            newSubscription.setStartDate(LocalDateTime.now());
            newSubscription.setFinishDate(LocalDateTime.now().plusDays(membership.getDuration()));
            SubscriptionEntity subscriptionSaved = this.subscriptionCrudRepository.save(newSubscription);
            return this.subscriptionMapper.suscriptionEntityToResponse(subscriptionSaved);
        }else{
           SubscriptionEntity subscription = this.subscriptionCrudRepository.findByMember_idMember(member.getIdMember())
                   .orElseThrow(() -> new IllegalArgumentException("Suscription not found."));
           subscription.setMembership(membership);
           subscription.setStartDate(LocalDateTime.now());
           subscription.setFinishDate(LocalDateTime.now().plusDays(membership.getDuration()));
           subscription.setStatus(true);
            SubscriptionEntity subscriptionSaved = this.subscriptionCrudRepository.save(subscription);
           return this.subscriptionMapper.suscriptionEntityToResponse(subscriptionSaved);
        }


    }

    @Override
    public SubscriptionResponse getByUser(Long userId) {
        SubscriptionEntity subscription =  this.subscriptionCrudRepository.findByMember_idMember(userId)
                .orElseThrow();

        return this.subscriptionMapper.suscriptionEntityToResponse(subscription);
    }

    @Override
    public boolean existsByMember_IdMember(Long idMember) {
        return this.subscriptionCrudRepository.existsByMember_idMember(idMember);
    }

    @Override
    public Optional<SubscriptionEntity> findByMember_IdMember(Long idMember) {
        return this.subscriptionCrudRepository.findByMember_idMember(idMember);
    }

    @Override
    public void delete(SubscriptionEntity subscription) {
        this.subscriptionCrudRepository.delete(subscription);
    }

    @Override
    public List<MostUsedActiveMembershipDto> findMostUsedMembership() {
        int year = LocalDateTime.now().getYear();
        List<Object[]> totalSubscriptionByMembership = this.subscriptionCrudRepository.findAllByMembershipsMostUsed(year);

        return totalSubscriptionByMembership
                .stream()
                .map(objects -> {
                    return new MostUsedActiveMembershipDto((String) objects[1], (Long) objects[0]);
                })
                .toList();
    }

    @Override
    public List<UserTypeloggedInDto> findTypesOfUserByMonth() {
        int year = LocalDateTime.now().getYear();
        List<Object[]> objectsUserBySubscriptions = this.subscriptionCrudRepository.findAllTotalSubscriptionByMonth(year);
        List<TotalVisitAccessesPerMonth> totalVisitAccessesPerMonthList = this.visitsPersistencePort.findAllTotalVisitsByMonth();
        List<UserTypeloggedInDto> result = new ArrayList<>();

        for (int month = 1; month <= 12 ; month++) {
            UserTypeloggedInDto userTypeloggedInDto = new UserTypeloggedInDto();
            userTypeloggedInDto.setMonth(month);
           for (Object[] object : objectsUserBySubscriptions){
               Integer monthObject = ((Number) object[0]).intValue();
               if(monthObject == month) {
                   userTypeloggedInDto.setTotalWithSubscription(( (Long) object[1]));
                   break;
               }
           }

           for (TotalVisitAccessesPerMonth visits : totalVisitAccessesPerMonthList) {
               if(visits.getMonth() == month) {
                   userTypeloggedInDto.setTotalWithoutSubscription(((Long) visits.getTotal()));
               }
           }
           result.add(userTypeloggedInDto);

        }

        return result;
    }

    @Override
    public TotalActiveAndInactiveMembers findAllToTalActiveAndInactiveMembers() {
        int year = LocalDateTime.now().getYear();
        ActiveInactiveCount objectQuery =  this.subscriptionCrudRepository.findAllActiveAndInactive(year);

        return TotalActiveAndInactiveMembers
                .builder()
                .activeMembers(objectQuery.getActives())
                .inactiveMembers(objectQuery.getInactives())
                .build();
    }

    @Override
    public SubscriptionStatus findSubscriptionStatus(Long dni) {

        SubscriptionStatusView infoStatus = this.subscriptionCrudRepository.findStatusSubscriptionByMemberId(dni)
                .orElseThrow(() -> new UsernameNotFoundException("Member not found with id: "+ dni));


        return SubscriptionStatus.builder()
                .endMembership(infoStatus.getEndSubscription())
                .statusSubscription(infoStatus.getStatusSubscription())
                .build();
    }


    // esta funcion solo es para la actualizacion de las membresias en el schedule que actualiza las membresias.
    public void saveDirect(SubscriptionEntity subscription) {
        this.subscriptionCrudRepository.save(subscription);
    }
}
