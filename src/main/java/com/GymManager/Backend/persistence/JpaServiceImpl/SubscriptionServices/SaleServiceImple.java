package com.GymManager.Backend.persistence.JpaServiceImpl.SubscriptionServices;

import com.GymManager.Backend.domain.dto.SaleAndSuscription.SaleDto;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SaleResponse;
import com.GymManager.Backend.domain.repository.GymMemberPersistencePort;
import com.GymManager.Backend.domain.repository.MembresiaRepository;
import com.GymManager.Backend.domain.repository.SalePersitencePort;
import com.GymManager.Backend.domain.service.SaleService;
import com.GymManager.Backend.persistence.entity.GymMembers;
import com.GymManager.Backend.persistence.entity.MembershipEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleServiceImple implements SaleService {
    private final SalePersitencePort salePersitencePort;
    private final GymMemberPersistencePort gymMemberPersistencePort;
    private final MembresiaRepository membresiaRepository;

    public SaleServiceImple(SalePersitencePort salePersitencePort, GymMemberPersistencePort gymMemberPersistencePort, MembresiaRepository membresiaRepository) {
        this.salePersitencePort = salePersitencePort;
        this.gymMemberPersistencePort = gymMemberPersistencePort;
        this.membresiaRepository = membresiaRepository;
    }

    @Override
    @Transactional
    public SaleResponse save(SaleDto saleDto) {
        try{
            if (saleDto.getPurchaseMethod().isEmpty()){
                throw new IllegalArgumentException("payment method no add.");
            }
            // Validar que el usuario si exista y la membresi
            GymMembers member = this.gymMemberPersistencePort.findById(saleDto.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("Member en sale no found: " + saleDto.getUserId()));

            MembershipEntity membership = this.membresiaRepository.findById(saleDto.getMembershipId())
                    .orElseThrow(() -> new IllegalArgumentException("Membership no found: " + saleDto.getMembershipId()));
            return this.salePersitencePort.save(saleDto, member, membership) ;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public SaleResponse findById(Integer id) {
        return null;
    }

    @Override
    public List<SaleResponse> getAll(Pageable pageable) {
        return List.of();
    }

    @Override
    public List<SaleResponse> findAllByMountByMonth() {
        return List.of();
    }

    @Override
    public List<SaleResponse> findByDateRange(LocalDateTime start, LocalDateTime end) {
        return List.of();
    }

    @Override
    public List<SaleResponse> findByCustomerId(Integer customerId) {
        return List.of();
    }

    @Override
    public List<SaleResponse> findByPaymentMethod(String method) {
        return List.of();
    }

    @Override
    public void clean() {

    }

    @Override
    public void deleteAll() {

    }
}
