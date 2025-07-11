package com.GymManager.Backend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "access_log")
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Builder
@AllArgsConstructor
public class AccessLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_access_log")
    private Integer idAccessLog;

    @ManyToOne
    @JoinColumn( name = "id_member", referencedColumnName = "id_member")
    private GymMembers user;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createDate;

}
