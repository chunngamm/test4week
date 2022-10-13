package com.sparta.homework.controller;


import com.sparta.homework.dto.MemberLoginRequestDto;
import com.sparta.homework.dto.SignupRequestDto;
import com.sparta.homework.security.TokenInfo;
import com.sparta.homework.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }


    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(@RequestBody SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "redirect:/user/login";
    }

    @PostMapping("/login")
    public TokenInfo login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        String memberId = memberLoginRequestDto.getMemberId();
        String password = memberLoginRequestDto.getPassword();
        TokenInfo tokenInfo = userService.login(memberId, password);
        return tokenInfo;
    }


    }

