package com.GymManager.Backend.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "sale_register_entity")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SaleRegisterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer member;

    @Column(nullable = false, length = 100)
    private String paymentMethod;

    @Column(nullable = false)
    private double amount;

    @CreatedDate
    private LocalDateTime createDate;

    @Column(nullable = false)
    private Integer membership;

    @Column(nullable = false, length = 100)
    private String receptionistName; // el recepcionista logeado que genero esta venta

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_sale", referencedColumnName = "id_subscription")
    private SubscriptionEntity subscription;


}
