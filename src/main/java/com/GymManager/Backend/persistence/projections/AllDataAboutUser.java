package com.GymManager.Backend.persistence.projections;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface AllDataAboutUser {
    // personal info
    Long getId();
    String getFullName();
    String getDni();
    LocalDate getDateOfBirth();
    Long getPhone();
    String getEmail();
    String getGender();
    LocalDateTime getCreateDate();

    // data of membership
    String getNameMembership();
    String getTypeMembership();

    // data about info of subscription
    Boolean getStateOfMembership();
    LocalDateTime getDateStart();
    LocalDateTime getDateFinished();
}
