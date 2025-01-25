package com.example.SitPass.service;


import com.example.SitPass.dto.LoginReqDto;
import com.example.SitPass.dto.LoginResDto;
import com.example.SitPass.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LoginService {

    LoginResDto login(LoginReqDto request);

    LoginResDto refreshToken(HttpServletRequest request, HttpServletResponse response);

}
