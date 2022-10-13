package com.sparta.homework.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;


@Setter
@Getter
public class SignupRequestDto {

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,12}$")
    private String username;
    private String password;
    private String passwordCheck;
    private String email;


}