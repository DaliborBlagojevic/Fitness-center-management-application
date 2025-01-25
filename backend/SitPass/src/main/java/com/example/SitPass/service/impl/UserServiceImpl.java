package com.example.SitPass.service.impl;

import com.example.SitPass.dto.AccountRequestDto;
import com.example.SitPass.dto.UserDto;
import com.example.SitPass.exception.BadRequestException;
import com.example.SitPass.exception.NotFoundException;
import com.example.SitPass.model.AccountRequest;
import com.example.SitPass.model.RequestStatus;
import com.example.SitPass.model.User;
import com.example.SitPass.repository.AccountRequestRepository;
import com.example.SitPass.repository.UserRepository;
import com.example.SitPass.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.SitPass.dto.UserDto.convertToDto;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final AccountRequestRepository accountRequestRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getModel(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    String errorMessage = String.format("User with email %s not found.", email);
                    return new NotFoundException(errorMessage);
                });
    }

    @Override
    public List<UserDto> findAll() {

        List<User> users = userRepository.findAll();

        return users.stream().map(UserDto::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto create(UserDto userDTO) {
        Optional<AccountRequest> accountRequestOptional = accountRequestRepository.findByEmail(userDTO.getEmail());


        AccountRequestDto accountRequestDto = AccountRequestDto.convertToDto(accountRequestOptional.get());
        if (accountRequestDto.getRequestStatus() == RequestStatus.PENDING ||
                accountRequestDto.getRequestStatus() == RequestStatus.REJECTED) {
        }

        return convertToDto(userRepository.save(userDTO.convertToModel()));
    }

    @Override
    public UserDto updateProfile(UserDto userDto, String email) {
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("User with email %s not found.", email)));

        userDto.setId(existingUser.getId());
        userDto.setCreatedAt(existingUser.getCreatedAt());
        userDto.setEmail(existingUser.getEmail());
        userDto.setPassword(passwordEncoder.encode(existingUser.getPassword()));
        userDto.setAddress(existingUser.getAddress());

        existingUser.setName(userDto.getName());
        existingUser.setSurname(userDto.getSurname());
        existingUser.setPhoneNumber(userDto.getPhoneNumber());
        existingUser.setBirthday(userDto.getBirthday());
        existingUser.setCity(userDto.getCity());
        existingUser.setZipCode(userDto.getZipCode());

        User savedUser = userRepository.save(existingUser);
        return  convertToDto(savedUser);
    }

    @Override
    public UserDto updatePassword(Long id, UserDto userDto) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s not found.", id)));

        existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User savedUser = userRepository.save(existingUser);
        return convertToDto(savedUser);
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s not found.", email)));
        return convertToDto(user);
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s not found.", id)));
        return convertToDto(user);
    }
}
