package com.salemarket.salemarket.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {

    private String token;
    private Long userId;
    private String username;
    public LoginResponseDto(String token, Long userId, String username) {
        this.token = token;
        this.userId = userId;
        this.username = username;


    }
}
