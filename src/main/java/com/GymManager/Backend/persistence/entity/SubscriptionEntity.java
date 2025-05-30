package com.GymManager.Backend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "subscription")
@Builder
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subscription") // nombre de la columna en SQL
    private Integer subscriptionId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id_member")
    private GymMembers member;

    @ManyToOne
    @JoinColumn(name = "id_membership", referencedColumnName = "id")
    private MembershipEntity membership;

    private Boolean status;

    @CreatedDate
    private LocalDateTime createdDate;

    private LocalDateTime startDate;

    private LocalDateTime finishDate;

    @OneToMany(mappedBy = "subscription")
    @JsonIgnore
    private List<SaleRegisterEntity> payments;


}
