package com.salemarket.salemarket.controller;

import com.salemarket.salemarket.dto.LoginRequestDto;
import com.salemarket.salemarket.dto.LoginResponseDto;
import com.salemarket.salemarket.dto.SignupRequestDto;
import com.salemarket.salemarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user/login")
    public ResponseEntity login(){
        return new ResponseEntity ("로그인 되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/user/login/fail")
    public ResponseEntity loginFail(){
        return new ResponseEntity ("로그인 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/user/signup")
    public ResponseEntity signup(@RequestBody SignupRequestDto signupRequestDto){
        boolean check = userService.signup(signupRequestDto);
        if(check){
            return new ResponseEntity("회원가입 되었습니다.", HttpStatus.OK);
        }else{
            return new ResponseEntity("아이디가 중복되었습니다.", HttpStatus.OK);
        }

    }

    @PostMapping("/user/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto){
        return userService.login(loginRequestDto);
    }
}
