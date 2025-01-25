package com.example.SitPass.dto;


import com.example.SitPass.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;

    private String email;

    private String password;

    private String name;

    private String surname;

    private LocalDate createdAt;

    private String phoneNumber;

    private LocalDate birthday;

    private String address;

    private String city;

    private String zipCode;


    public static UserDto convertToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .surname(user.getSurname())
                .createdAt(user.getCreatedAt())
                .phoneNumber(user.getPhoneNumber())
                .birthday(user.getBirthday())
                .address(user.getAddress())
                .city(user.getCity())
                .zipCode(user.getZipCode())
                .build();
    }

    public User convertToModel() {
        return User.builder()
                .id(getId())
                .email(getEmail())
                .password(getPassword())
                .name(getName())
                .surname(getSurname())
                .createdAt(getCreatedAt())
                .phoneNumber(getPhoneNumber())
                .birthday(getBirthday())
                .address(getAddress())
                .city(getCity())
                .zipCode(getZipCode())
                .build();
    }

}
