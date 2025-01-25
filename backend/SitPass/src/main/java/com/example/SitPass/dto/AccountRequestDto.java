package com.example.SitPass.dto;

import com.example.SitPass.model.AccountRequest;
import com.example.SitPass.model.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AccountRequestDto {

    private Long id;
    private String email;
    private String password;
    private LocalDate createdAt;
    private String address;
    private RequestStatus requestStatus;
    private String rejectReason;

    public static AccountRequestDto convertToDto(AccountRequest accountRequest) {
        return AccountRequestDto.builder()
                .id(accountRequest.getId())
                .email(accountRequest.getEmail())
                .password(accountRequest.getPassword())
                .createdAt(accountRequest.getCreatedAt())
                .address(accountRequest.getAddress())
                .requestStatus(accountRequest.getRequestStatus())
                .rejectReason(accountRequest.getRejectReason())
                .build();
    }

    public AccountRequest convertToModel() {
        return AccountRequest.builder()
                .id(getId())
                .email(getEmail())
                .password(getPassword())
                .createdAt(getCreatedAt())
                .address(getAddress())
                .requestStatus(getRequestStatus())
                .rejectReason(getRejectReason())
                .build();
    }

}
