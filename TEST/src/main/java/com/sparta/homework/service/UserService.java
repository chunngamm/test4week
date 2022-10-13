package com.sparta.homework.service;

import com.sparta.homework.dto.SignupRequestDto;
import com.sparta.homework.domain.User;

import com.sparta.homework.repository.UserRepository;
import com.sparta.homework.security.JwtTokenProvider;
import com.sparta.homework.security.TokenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;



    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManagerBuilder authenticationManagerBuilder, JwtTokenProvider jwtTokenProvider) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    //회원가입
    public void registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String passwordCheck = requestDto.getPasswordCheck();
        String email =requestDto.getEmail();


        // 회원 ID 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        // 비밀번호 일치불일치 확인
        if(!password.equals(passwordCheck)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 패스워드 암호화
        password = passwordEncoder.encode(requestDto.getPassword());



        User user = new User(username, password,email);
        userRepository.save(user);
    }




    @Transactional
    public TokenInfo login(String memberId, String password) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberId, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return tokenInfo;
    }


}
