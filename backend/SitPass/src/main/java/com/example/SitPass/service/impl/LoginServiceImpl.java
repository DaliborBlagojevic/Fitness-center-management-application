package com.example.SitPass.service.impl;

import com.example.SitPass.dto.LoginReqDto;
import com.example.SitPass.dto.LoginResDto;
import com.example.SitPass.exception.UnauthorizedException;
import com.example.SitPass.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;


@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    public LoginResDto login(LoginReqDto request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

            return LoginResDto.builder()
                    .accessToken(jwtService.generateToken(userDetails))
                    .refreshToken(jwtService.generateRefreshToken(userDetails))
                    .build();
        } catch (UnauthorizedException e) {
            throw new UnauthorizedException("Authentication failed. Please check your credentials.");
        }
    }

    @Override
    public LoginResDto refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String refreshToken = authHeader.substring(7);

        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtService.extractUsername(refreshToken));

        if (jwtService.isTokenValid(refreshToken, userDetails)) {
            return LoginResDto.builder()
                    .accessToken(jwtService.generateToken(userDetails))
                    .refreshToken(refreshToken)
                    .build();
        } else {
            throw new UnauthorizedException("Refresh token is not valid");
        }
    }
}