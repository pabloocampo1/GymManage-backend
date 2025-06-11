package com.GymManager.Backend.persistence.JpaServiceImpl.SubscriptionServices;

import com.GymManager.Backend.domain.dto.DashboardDtos.TotalMonthlyRevenueDto;
import com.GymManager.Backend.domain.dto.DashboardDtos.TotalRevenueByMembershipDto;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SaleDto;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SaleResponse;
import com.GymManager.Backend.domain.repository.GymMemberPersistencePort;
import com.GymManager.Backend.domain.repository.MembresiaRepository;
import com.GymManager.Backend.domain.repository.SalePersitencePort;
import com.GymManager.Backend.domain.service.SaleService;
import com.GymManager.Backend.persistence.entity.GymMembers;
import com.GymManager.Backend.persistence.entity.MembershipEntity;
import com.GymManager.Backend.persistence.entity.RegularVisitEntity;
import com.GymManager.Backend.persistence.entity.SaleRegisterEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
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
        try {
            if (saleDto.getPurchaseMethod().isEmpty()) {
                throw new IllegalArgumentException("payment method no add.");
            }

            GymMembers member = this.gymMemberPersistencePort.findById(saleDto.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("Member en sale no found: " + saleDto.getUserId()));

            MembershipEntity membership = this.membresiaRepository.findById(saleDto.getMembershipId())
                    .orElseThrow(() -> new IllegalArgumentException("Membership no found: " + saleDto.getMembershipId()));
            return this.salePersitencePort.save(saleDto, member, membership);
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
    public List<TotalMonthlyRevenueDto> findAllByMountByMonth() {
        return this.salePersitencePort.findAllByMountByMonth();
    }

    @Override
    public TotalRevenueByMembershipDto findAllAmountsByMembership() {
        return this.salePersitencePort.getTotalRevenueByMembership();
    }

    @Override
    public List<SaleRegisterEntity> findByToday() {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = LocalDate.now().atTime(LocalTime.MAX);
        return this.salePersitencePort.findByDateRange(start, end);
    }

    @Override
    public List<SaleRegisterEntity> findByMonth() {
        YearMonth month = YearMonth.now();
        LocalDateTime start = month.atDay(1).atStartOfDay();
        LocalDateTime end = month.atEndOfMonth().atTime(LocalTime.MAX);
        return this.salePersitencePort.findByDateRange(start, end);
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
    public void saveSaleOfVisit(RegularVisitEntity regularVisitEntity) {
        MembershipEntity membershipRegular = this.membresiaRepository.findRegularMembership()
                .orElseThrow(() -> new RuntimeException("Membership regular no exist. "));

        SaleRegisterEntity newSale = new SaleRegisterEntity();
        newSale.setMember(membershipRegular.getId());
        newSale.setAmount(membershipRegular.getPrice());
        newSale.setMembership(membershipRegular.getId());
        newSale.setPaymentMethod("Efectivo");
        newSale.setReceptionistName("Sistema");

       this.salePersitencePort.saveSaleDirect(newSale);
    }

    @Override
    public void clean() {

    }

    @Override
    public void deleteAll() {

    }
}
