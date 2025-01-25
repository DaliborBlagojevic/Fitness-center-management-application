package com.example.SitPass.service;

import com.example.SitPass.dto.AccountRequestDto;
import com.example.SitPass.model.AccountRequest;

import java.util.List;

public interface RegistrationService {

    List<AccountRequestDto> findAllPending();

    AccountRequestDto sendAccountRequest(AccountRequestDto accountRequestDto);

    AccountRequestDto handleAccountRequest(AccountRequestDto accountRequestDto);
}
