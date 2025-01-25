package com.example.SitPass.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "account_request")
public class AccountRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private LocalDate createdAt;

    @Column
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name="request_status", nullable = false)
    private RequestStatus requestStatus;


    @Column
    private String rejectReason;


}
