package com.salemarket.salemarket.dto;

import com.salemarket.salemarket.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequestDto {

    private String username;
    private String password;
    private String region;

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .region(region)
                .build();
    }

    public void passwordUpdate(String encodePassword) {
        this.password = encodePassword;
    }
}
