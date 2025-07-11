
package com.GymManager.Backend.persistence.JpaRepositoriImpl;


import com.GymManager.Backend.domain.repository.MembresiaRepository;
import com.GymManager.Backend.persistence.crudRepository.MembresiaCrudRepo;
import com.GymManager.Backend.persistence.entity.MembershipEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MembresiaRepositoryImpl implements MembresiaRepository {

    private final MembresiaCrudRepo crudRepository;

    @Override
    public MembershipEntity save(MembershipEntity membresia) {
        membresia.setAvailable(true);
        return this.crudRepository.save(membresia);
    }

    @Override
    public Optional<MembershipEntity> findById(Integer id) {
        return crudRepository.findById(id);
    }

    @Override
    public List<MembershipEntity> findAll() {
        return crudRepository.findAllByAvailableTrue();
    }

    @Override
    public MembershipEntity update(MembershipEntity membresia) {
        return this.crudRepository.save(membresia);
    }

    @Override
    public Boolean existById(Integer id) {
        return this.crudRepository.existsByIdAndAvailableTrue(id);
    }

    @Override
    public Optional<MembershipEntity> findRegularMembership() {
        return this.crudRepository.findMembershipRegular();
    }

    @Override
    public void deleteById(Integer id) {
        if(!this.crudRepository.existsById(id)){
            throw  new RuntimeException("membership no exist.");
        }
        this.crudRepository.ChangeState(id);
    }
}