package com.GymManager.Backend.web.controller;

import com.GymManager.Backend.domain.service.AccessLogService;
import com.GymManager.Backend.domain.service.VisitsService;
import com.GymManager.Backend.persistence.entity.AccessLogEntity;
import com.GymManager.Backend.persistence.entity.GymMembers;
import com.GymManager.Backend.persistence.entity.RegularVisitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {
    private final AccessLogService accessLogService;
    private final VisitsService visitsService;

    @Autowired
    public ActivityController(AccessLogService accessLogService, VisitsService visitsService) {
        this.accessLogService = accessLogService;
        this.visitsService = visitsService;
    }

    @GetMapping("/getAllByMemberToday")
    public ResponseEntity<List<AccessLogEntity>> getAllMembersToday() {
        return ResponseEntity.ok(this.accessLogService.getAllMemberToday());
    }

    @GetMapping("/getAllByMemberByMonth")
    public ResponseEntity<List<AccessLogEntity>> getAllMembersMonth() {
        return ResponseEntity.ok(this.accessLogService.getAllMemberMonth());
    }

    @GetMapping("/getAllByVisitsToday")
    public ResponseEntity<List<AccessLogEntity>> getAllVisitsToday() {
        List<RegularVisitEntity> visits = this.visitsService.getAllByToday();

        List<AccessLogEntity> access = visits.stream().map(
                (regularVisitEntity) -> {
                    AccessLogEntity accessLogEntity = new AccessLogEntity();
                    GymMembers gymMembers = new GymMembers();
                    gymMembers.setFullName(regularVisitEntity.getFullName());
                    gymMembers.setIdentificationNumber(Integer.valueOf(String.valueOf(regularVisitEntity.getDocumentId())));
                    gymMembers.setPhone(Math.toIntExact(regularVisitEntity.getPhoneNumber()));
                    accessLogEntity.setUser(gymMembers);
                    accessLogEntity.setCreateDate(regularVisitEntity.getVisitDate());
                    accessLogEntity.setIdAccessLog(Math.toIntExact(regularVisitEntity.getId()));
                    return accessLogEntity;
                }
        ).toList();


        return ResponseEntity.ok(access);
    }

    @GetMapping("/getAllByVisitsByMonth")
    public ResponseEntity<List<AccessLogEntity>> getAllVisitsMonth() {
        List<RegularVisitEntity> visits = this.visitsService.getAllByMonth();

        List<AccessLogEntity> access = visits.stream().map(
                (regularVisitEntity) -> {
                    AccessLogEntity accessLogEntity = new AccessLogEntity();
                    GymMembers gymMembers = new GymMembers();
                    gymMembers.setFullName(regularVisitEntity.getFullName());
                    gymMembers.setIdentificationNumber(Integer.valueOf(String.valueOf(regularVisitEntity.getDocumentId())));
                    gymMembers.setPhone(Math.toIntExact(regularVisitEntity.getPhoneNumber()));
                    accessLogEntity.setUser(gymMembers);
                    accessLogEntity.setCreateDate(regularVisitEntity.getVisitDate());
                    accessLogEntity.setIdAccessLog(Math.toIntExact(regularVisitEntity.getId()));
                    return accessLogEntity;
                }
        ).toList();

        return ResponseEntity.ok(access);
    }
    @GetMapping("/getAllToday")
    public ResponseEntity<List<AccessLogEntity>> getAllToday() {
        List<AccessLogEntity> access = this.accessLogService.getAllMemberToday();
        List<RegularVisitEntity> visits = this.visitsService.getAllByToday();

        List<AccessLogEntity> regularVisitToAccess = visits.stream().map(
                (regularVisitEntity) -> {
                    AccessLogEntity accessLogEntity = new AccessLogEntity();
                    GymMembers gymMembers = new GymMembers();
                    gymMembers.setFullName(regularVisitEntity.getFullName());
                    gymMembers.setIdentificationNumber(Integer.valueOf(String.valueOf(regularVisitEntity.getDocumentId())));
                    gymMembers.setPhone(Math.toIntExact(regularVisitEntity.getPhoneNumber()));
                    accessLogEntity.setUser(gymMembers);
                    accessLogEntity.setCreateDate(regularVisitEntity.getVisitDate());
                    accessLogEntity.setIdAccessLog(Math.toIntExact(regularVisitEntity.getId()));
                    return accessLogEntity;
                }
        ).toList();

        List<AccessLogEntity> result = Stream.concat(access.stream(), regularVisitToAccess.stream())
                .sorted(Comparator.comparing(AccessLogEntity::getCreateDate))
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAllMonth")
    public ResponseEntity<List<AccessLogEntity>> getAllMonth() {
        List<AccessLogEntity> access = this.accessLogService.getAllMemberMonth();
        List<RegularVisitEntity> visits = this.visitsService.getAllByMonth();

        List<AccessLogEntity> regularVisitToAccess = visits.stream().map(
                (regularVisitEntity) -> {
                    AccessLogEntity accessLogEntity = new AccessLogEntity();
                    GymMembers gymMembers = new GymMembers();
                    gymMembers.setFullName(regularVisitEntity.getFullName());
                    gymMembers.setIdentificationNumber(Integer.valueOf(String.valueOf(regularVisitEntity.getDocumentId())));
                    gymMembers.setPhone(Math.toIntExact(regularVisitEntity.getPhoneNumber()));
                    accessLogEntity.setUser(gymMembers);
                    accessLogEntity.setCreateDate(regularVisitEntity.getVisitDate());
                    accessLogEntity.setIdAccessLog(Math.toIntExact(regularVisitEntity.getId()));
                    return accessLogEntity;
                }
        ).toList();

        List<AccessLogEntity> result = Stream.concat(access.stream(), regularVisitToAccess.stream())
                .sorted(Comparator.comparing(AccessLogEntity::getCreateDate))
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }


}
