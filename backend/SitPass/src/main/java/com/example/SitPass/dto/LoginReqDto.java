package com.example.SitPass.dto;


import com.example.SitPass.model.User;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class LoginReqDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;


}
