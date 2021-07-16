package com.salemarket.salemarket.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {

    private Long id;
    public LoginResponseDto(Long id) {
        this.id = id;

    }
}
