package com.GymManager.Backend.persistence.JpaServiceImpl;

import com.GymManager.Backend.domain.dto.DashboardDtos.AverageGenderDistributionDto;
import com.GymManager.Backend.domain.dto.DashboardDtos.TotalActiveAndInactiveMembers;
import com.GymManager.Backend.domain.dto.GymMember.GymMemberDto;
import com.GymManager.Backend.domain.dto.GymMember.GymMemberFullData;
import com.GymManager.Backend.domain.dto.GymMember.GymMemberRequest;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SaleDto;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SubscriptionResponse;
import com.GymManager.Backend.domain.repository.GymMemberPersistencePort;
import com.GymManager.Backend.domain.service.GymMemberService;
import com.GymManager.Backend.domain.service.SaleService;
import com.GymManager.Backend.domain.service.SubscriptionService;
import com.GymManager.Backend.persistence.Mappers.GymMemberMapper;
import com.GymManager.Backend.persistence.entity.GymMembers;
import com.GymManager.Backend.persistence.projections.AllDataAboutUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GymMemberServiceImpl implements GymMemberService {
    private final GymMemberPersistencePort gymMemberPersistencePort;
    private final GymMemberMapper gymMemberMapper;
    private final SaleService saleService;
    private final SubscriptionService subscriptionService;

    @Autowired
    public GymMemberServiceImpl(GymMemberPersistencePort gymMemberPersistencePort, GymMemberMapper gymMemberMapper, SaleService saleService, SubscriptionService subscriptionService) {
        this.gymMemberPersistencePort = gymMemberPersistencePort;
        this.gymMemberMapper = gymMemberMapper;
        this.saleService = saleService;
        this.subscriptionService = subscriptionService;
    }

    @Override
    @Transactional
    public GymMemberDto save(GymMemberRequest dto) {
       try {
           if (dto.getGymMemberDto() == null) {
               throw new IllegalArgumentException("GymMemberDto no puede ser null.");
           }
           if (dto.getGymMemberDto().getId() != null) {
               dto.getGymMemberDto().setId(null);
           }
           if (gymMemberPersistencePort.findByIdentificationNumber(dto.getGymMemberDto().getIdentificationNumber()).isPresent()) {
               throw new UsernameNotFoundException("Miembro ya existe con ID: " + dto.getGymMemberDto().getIdentificationNumber());
           }
           GymMembers entity = gymMemberMapper.toEntity(dto.getGymMemberDto());
           GymMembers savedEntity = gymMemberPersistencePort.save(entity);

           this.checkAndCreateSale(dto.getSaleDto(), savedEntity);

           return gymMemberMapper.toDto(savedEntity);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }



    @Transactional
    public void checkAndCreateSale(SaleDto saleDto, GymMembers gymMembers) {
        if (saleDto != null && saleDto.getMembershipId() != null) {
            saleDto.setUserId(gymMembers.getIdMember());
            this.saleService.save(saleDto);
        }
    }


    @Override
  // @Cacheable(value = "members")
    public List<GymMemberDto> getAll() {
        return gymMemberPersistencePort.findAll().stream()
                .map(gymMemberMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GymMemberDto getById(Long id) {
        GymMembers entity = gymMemberPersistencePort.findById(id)
                .orElseThrow(() -> new RuntimeException("Miembro no encontrado con ID: " + id));
        return gymMemberMapper.toDto(entity);
    }

    @Override
    public Optional<GymMembers> getByIdDirect(Long id) {
        return this.gymMemberPersistencePort.findById(id);
    }

    @Override
    public void delete(Long id) {
        if (!gymMemberPersistencePort.findById(id).isPresent()) {
            throw new RuntimeException("No se puede eliminar. Miembro no encontrado con ID: " + id);
        }
        gymMemberPersistencePort.deleteById(id);
    }

    @Override
    public GymMemberDto update(Long id, GymMemberDto dto) {
        GymMembers entity = this.gymMemberPersistencePort.
                findById(id).
                orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND + " + id)) ;

        entity.setFullName(dto.getFullName());
        entity.setBirthDate(dto.getBirthDate());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setGender(dto.getGender());
        entity.setEmergencyPhone(dto.getEmergencyPhone());
        entity.setIdentificationNumber(dto.getIdentificationNumber());

        GymMembers entitySaved =  this.gymMemberPersistencePort.save(entity);
        return gymMemberMapper.toDto(entitySaved);
    }

    @Override
    public List<SubscriptionResponse> getAllByParam(String param) {
        List<GymMembers> members = this.gymMemberPersistencePort.findAllByParam(param);

        List<SubscriptionResponse> responseSubscriptions = members.stream().map(member -> {
            SubscriptionResponse subscriptionByUser = this.subscriptionService.getByUser(member.getIdMember());
            return subscriptionByUser;
        }).toList() ;

        return responseSubscriptions;
    }

    @Override
    public GymMemberFullData getFullDataMember(@Valid Long userId) {
        return this.gymMemberPersistencePort.getFullData(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Member not found + " + userId));
    }

    @Override
    public List<AverageGenderDistributionDto> getTotalMemberByGender() {
        return this.gymMemberPersistencePort.findTotalMembersByGender();
    }

    @Override
    public List<GymMemberFullData> getLastRegisteredMember() {
        List<AllDataAboutUser> members = this.gymMemberPersistencePort.findAllLastRegisteredMembers();
        return members
                .stream()
                .map(this.gymMemberMapper::toFullData)
                .toList();
    }


}
