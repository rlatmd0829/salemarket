package com.salemarket.salemarket.controller;

import com.salemarket.salemarket.dto.SignupRequestDto;
import com.salemarket.salemarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity signup(@RequestBody SignupRequestDto signupRequestDto){
        userService.signup(signupRequestDto);
        return new ResponseEntity("회원가입 되었습니다.", HttpStatus.OK);
    }

//    @PostMapping("/user/login")
//    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto){
//        return userService.login(loginRequestDto);
//    }
}
