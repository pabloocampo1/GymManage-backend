package com.GymManager.Backend.persistence.JpaRepositoriImpl;

import com.GymManager.Backend.domain.dto.DashboardDtos.TotalMonthlyRevenueDto;
import com.GymManager.Backend.domain.dto.DashboardDtos.TotalRevenueByMembershipDto;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SaleDto;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SaleResponse;
import com.GymManager.Backend.domain.dto.SaleAndSuscription.SubscriptionDto;
import com.GymManager.Backend.domain.repository.GymMemberPersistencePort;
import com.GymManager.Backend.domain.repository.MembresiaRepository;
import com.GymManager.Backend.domain.repository.SalePersitencePort;
import com.GymManager.Backend.domain.repository.SubscriptionPersistencePort;
import com.GymManager.Backend.persistence.Mappers.SaleAndSuscriptionMapper.SaleMapper;
import com.GymManager.Backend.persistence.crudRepository.SaleCrudRepository;
import com.GymManager.Backend.persistence.entity.GymMembers;
import com.GymManager.Backend.persistence.entity.MembershipEntity;
import com.GymManager.Backend.persistence.entity.SaleRegisterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class SaleJpaAdapter implements SalePersitencePort {
    private final SaleCrudRepository saleCrudRepository;
    private final GymMemberPersistencePort gymMemberPersistencePort;
    private final SaleMapper saleMapper;
    private final SubscriptionPersistencePort subscriptionPersistencePort;
    private final MembresiaRepository membresiaRepository;

    @Autowired
    public SaleJpaAdapter(SaleCrudRepository saleCrudRepository, GymMemberPersistencePort gymMemberPersistencePort, SaleMapper saleMapper, SubscriptionPersistencePort subscriptionPersistencePort, MembresiaRepository membresiaRepository) {
        this.saleCrudRepository = saleCrudRepository;
        this.gymMemberPersistencePort = gymMemberPersistencePort;
        this.saleMapper = saleMapper;
        this.subscriptionPersistencePort = subscriptionPersistencePort;
        this.membresiaRepository = membresiaRepository;
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

    // this method is only for register regular visits, no use in another implements
    @Override
    public void saveSaleDirect(SaleRegisterEntity saleRegisterEntity) {
        this.saleCrudRepository.save(saleRegisterEntity);
    }

    @Override
    public TotalRevenueByMembershipDto getTotalRevenueByMembership() {
        int year = LocalDateTime.now().getYear();
        List<MembershipEntity> allMembership = this.membresiaRepository.findAll();
        HashMap<String, List<Double>> result = new HashMap<>();

        for (MembershipEntity m : allMembership){
            List<Double> amounts = new ArrayList<>(Collections.nCopies(12,0.0));

            List<Object[]> data = this.saleCrudRepository.findByMembership(m.getId(), year);
            for (Object[] data_amount : data){
                Integer month = (Integer) data_amount[0];
                Double amount = (double) data_amount[1];
                amounts.set(month - 1, amount);
            }

            result.put(m.getTitle(), amounts);

        }

        return new TotalRevenueByMembershipDto(result);
    }

    @Override
    public List<TotalMonthlyRevenueDto> findAllByMountByMonth() {
        int year = LocalDateTime.now().getYear();
        // Trae los meses con ventas
        List<Object[]> salesObject = this.saleCrudRepository.findAllTotalSalesByMonth(year);

        // Mapea los meses que sí tienen ventas
        Map<Integer, Double> monthToAmount = salesObject.stream()
                .collect(Collectors.toMap(
                        obj -> (Integer) obj[0],
                        obj -> (Double) obj[1]
                ));


        List<TotalMonthlyRevenueDto> completeResult = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            double amount = monthToAmount.getOrDefault(month, 0.0);
            completeResult.add(new TotalMonthlyRevenueDto(month, amount));
        }

        return completeResult;
    }


    @Override
    public List<SaleRegisterEntity> findByDateRange(LocalDateTime start, LocalDateTime end) {
        int year = LocalDateTime.now().getYear();
        return this.saleCrudRepository.findByDateRange(year,start, end);
    }

    @Override
    public List<SaleRegisterEntity> findByCustomerId(Integer customerId) {
        return List.of();
    }

    @Override
    public List<SaleResponse> findByPaymentMethod(String method) {
        return List.of();
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
