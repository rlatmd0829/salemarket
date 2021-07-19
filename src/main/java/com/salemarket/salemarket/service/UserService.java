package com.salemarket.salemarket.service;

import com.salemarket.salemarket.config.jwt.JwtTokenProvider;
import com.salemarket.salemarket.dto.LoginRequestDto;
import com.salemarket.salemarket.dto.LoginResponseDto;
import com.salemarket.salemarket.dto.SignupRequestDto;
import com.salemarket.salemarket.model.User;
import com.salemarket.salemarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public boolean signup(SignupRequestDto signupRequestDto) {
        User user = userRepository.findByUsername(signupRequestDto.getUsername());
        if (user == null){
            String encodPassword = bCryptPasswordEncoder.encode(signupRequestDto.getPassword());
            signupRequestDto.passwordUpdate(encodPassword);
            userRepository.save(signupRequestDto.toEntity());
            return true;
        }else{
            return false;
        }

    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByUsername(loginRequestDto.getUsername());
        if(user == null){
            throw new IllegalStateException("가입되지 않은 아이디 입니다.");
        }
        if(!bCryptPasswordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())){
            throw new IllegalStateException("잘못된 비밀번호 입니다.");
        }
        return new LoginResponseDto(jwtTokenProvider.createToken(user.getUsername()),user.getId(), user.getUsername());
    }
}
