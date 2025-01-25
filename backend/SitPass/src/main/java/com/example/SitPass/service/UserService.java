package com.example.SitPass.service;

import com.example.SitPass.dto.UserDto;
import com.example.SitPass.model.User;

import java.util.List;

public interface UserService {


    List<UserDto> findAll();
    UserDto create(UserDto userDto);

    UserDto updateProfile(UserDto userDto, String email);

    UserDto updatePassword(Long id, UserDto userDto);

    User getModel(String email);

    UserDto findById(Long id);

    UserDto findByEmail(String email);
}
