package com.GymManager.Backend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "membership_entity")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembershipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50, unique = true)
    private String title;

    @Column(nullable = false, length = 100)
    private String type;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false)
    private Double price;

    @ElementCollection
    @CollectionTable(name = "membership_benefits", 
                    joinColumns = @JoinColumn(name = "membership_id"))
    @Column(name = "benefit")
    private List<String> benefits = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

    private Boolean available = true;

    @OneToMany(mappedBy = "membership")
    @JsonIgnore
    private List<SubscriptionEntity> suscriptionEntities;

}
