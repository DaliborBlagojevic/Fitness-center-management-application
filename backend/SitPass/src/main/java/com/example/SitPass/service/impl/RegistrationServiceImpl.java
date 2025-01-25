package com.example.SitPass.service.impl;

import com.example.SitPass.dto.AccountRequestDto;
import com.example.SitPass.dto.UserDto;
import com.example.SitPass.model.AccountRequest;
import com.example.SitPass.model.RequestStatus;
import com.example.SitPass.repository.AccountRequestRepository;
import com.example.SitPass.repository.UserRepository;
import com.example.SitPass.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.SitPass.dto.AccountRequestDto.convertToDto;

@RequiredArgsConstructor
@Service
public class RegistrationServiceImpl implements com.example.SitPass.service.RegistrationService {

    private final UserService userService;
    private final AccountRequestRepository accountRequestRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<AccountRequestDto> findAllPending() {

        List<AccountRequest> accountRequests = accountRequestRepository.findAllPending();
        return accountRequests.stream()
                .map(AccountRequestDto::convertToDto)
                .collect(Collectors.toList());

    }

    @Override
    public AccountRequestDto sendAccountRequest(AccountRequestDto accountRequestDto) {
        accountRequestRepository.findByEmail(accountRequestDto.getEmail())
                .ifPresent(accountRequest -> {
                    try {
                        throw new BadRequestException(String.format("Account with email %s is already in the database", accountRequestDto.getEmail()));
                    } catch (BadRequestException e) {
                        throw new RuntimeException(e);
                    }
                });
        accountRequestDto.setRequestStatus(RequestStatus.PENDING);
        accountRequestDto.setCreatedAt(LocalDate.now());
        return convertToDto(accountRequestRepository.save(accountRequestDto.convertToModel()));
    }


    @Override
    public  AccountRequestDto handleAccountRequest(AccountRequestDto accountRequestDto) {
        AccountRequest existingAccountRequest = accountRequestRepository.findById(accountRequestDto.getId())
                .orElseThrow();
    existingAccountRequest.setRequestStatus(accountRequestDto.getRequestStatus());
    String status = existingAccountRequest.getRequestStatus().toString();
    if(status.equals("REJECTED")) {
        existingAccountRequest.setRejectReason(accountRequestDto.getRejectReason());

    }
    AccountRequest savedAccountRequest = accountRequestRepository.save(existingAccountRequest);
    if (status.equals("ACCEPTED")) {
        UserDto userDto = new UserDto();
        userDto.setCreatedAt(LocalDate.now());
        userDto.setEmail(savedAccountRequest.getEmail());
        userDto.setPassword(passwordEncoder.encode(savedAccountRequest.getPassword()));
        userDto.setAddress(savedAccountRequest.getAddress());
        userService.create(userDto);
    }

    return convertToDto(savedAccountRequest);

    }



}

