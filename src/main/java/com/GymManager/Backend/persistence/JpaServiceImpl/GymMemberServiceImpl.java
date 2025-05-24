package com.GymManager.Backend.persistence.JpaServiceImpl;

import com.GymManager.Backend.domain.dto.GymMember.GymMemberDto;
import com.GymManager.Backend.domain.dto.GymMember.GymMemberRequest;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SaleDto;
import com.GymManager.Backend.domain.repository.GymMemberPersistencePort;
import com.GymManager.Backend.domain.service.GymMemberService;
import com.GymManager.Backend.domain.service.SaleService;
import com.GymManager.Backend.persistence.Mappers.GymMemberMapper;
import com.GymManager.Backend.persistence.entity.GymMembers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GymMemberServiceImpl implements GymMemberService {
    private final GymMemberPersistencePort gymMemberPersistencePort;
    private final GymMemberMapper gymMemberMapper;
    private final SaleService saleService;

    @Autowired
    public GymMemberServiceImpl(GymMemberPersistencePort gymMemberPersistencePort, GymMemberMapper gymMemberMapper, SaleService saleService) {
        this.gymMemberPersistencePort = gymMemberPersistencePort;
        this.gymMemberMapper = gymMemberMapper;
        this.saleService = saleService;
    }

    @Override
    @Transactional
    public GymMemberDto save(GymMemberRequest dto) {
        try {
            // validar que no tenga un id
            if (dto.getGymMemberDto().getId() != null) {
                dto.getGymMemberDto().setId(null);
            }
            // validar que la identificacion si sea correcta y unica
            if (gymMemberPersistencePort.findByIdentificationNumber(dto.getGymMemberDto().getIdentificationNumber()).isPresent()) {
                throw new UsernameNotFoundException("No se puede crear. Miembro encontrado con ID: " + dto.getGymMemberDto().getIdentificationNumber());
            }

            GymMembers entity = gymMemberMapper.toEntity(dto.getGymMemberDto());
            GymMembers savedEntity = gymMemberPersistencePort.save(entity);
            System.out.println("id del nuevo usuario: " + savedEntity.getIdMember());
            this.checkAndCreateSale(dto.getSaleDto(), savedEntity);
            return gymMemberMapper.toDto(savedEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Transactional
    public void checkAndCreateSale(SaleDto saleDto, GymMembers gymMembers) {
        // crear la venta si se le asigno una membresia
        if (saleDto.getMembershipId() != null) {
            try{
                SaleDto sale = saleDto;
                saleDto.setUserId(gymMembers.getIdMember());
                this.saleService.save(saleDto);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<GymMemberDto> getAll() {
        return gymMemberPersistencePort.findAll().stream()
                .map(gymMemberMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GymMemberDto getById(Integer id) {
        GymMembers entity = gymMemberPersistencePort.findById(id)
                .orElseThrow(() -> new RuntimeException("Miembro no encontrado con ID: " + id));
        return gymMemberMapper.toDto(entity);
    }

    @Override
    public void delete(Integer id) {
        if (!gymMemberPersistencePort.findById(id).isPresent()) {
            throw new RuntimeException("No se puede eliminar. Miembro no encontrado con ID: " + id);
        }
        gymMemberPersistencePort.deleteById(id);
    }

    @Override
    public GymMemberDto update(Integer id, GymMemberDto dto) {
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

}
