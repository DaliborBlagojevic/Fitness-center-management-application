package com.example.SitPass.controller;

import com.example.SitPass.dto.AccountRequestDto;
import com.example.SitPass.dto.LoginReqDto;
import com.example.SitPass.dto.LoginResDto;
import com.example.SitPass.service.LoginService;
import com.example.SitPass.service.RegistrationService;
import com.example.SitPass.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final RegistrationService registrationService;

    private final UserService userService;

    private final LoginService loginService;

    @GetMapping("/requests")
    public ResponseEntity<List<AccountRequestDto>> findAllPendingAccountRequests() {
        return ResponseEntity.ok(registrationService.findAllPending());
    }

    @PostMapping()
    public ResponseEntity<AccountRequestDto> sendAccountRequest(@RequestBody AccountRequestDto accountRequestDto) {
        return ResponseEntity.status(CREATED).body(registrationService.sendAccountRequest(accountRequestDto));
    }


    @PutMapping("/requests/{id}")
    public  ResponseEntity<AccountRequestDto> handleAccountRequest(@RequestBody AccountRequestDto accountRequestDto) {
        return ResponseEntity.ok(registrationService.handleAccountRequest(accountRequestDto));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResDto> loginUser(@RequestBody LoginReqDto loginReqDto) {
        return ResponseEntity.ok(loginService.login(loginReqDto));
    }



    @PostMapping("/refresh-token")
    public ResponseEntity<LoginResDto> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) {
        return ResponseEntity.ok(loginService.refreshToken(request, response));
    }



}
