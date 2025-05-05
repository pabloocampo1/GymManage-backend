package com.GymManager.Backend.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")

@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user;

    @Column(name = "username", nullable = false,unique = true)
    private String username;

    @Column(name = "password", nullable = false, unique = true)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "available")
    private Boolean available;

    @ManyToOne
    @JoinColumn(name = "id_role", referencedColumnName = "id_role")
    @ToString.Exclude
    private RoleUserEntity role;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

}
