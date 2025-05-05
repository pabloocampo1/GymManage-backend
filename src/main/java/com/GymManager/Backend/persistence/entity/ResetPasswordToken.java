package com.GymManager.Backend.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "reset_password_token")
@Data
@NoArgsConstructor
public class ResetPasswordToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Date expirateDate;

    @Column(nullable = false, unique = true)
    private String token;


    public ResetPasswordToken(String email, Date expirateDate, String token) {
        this.email = email;
        this.expirateDate = expirateDate;
        this.token = token;
    }

}


