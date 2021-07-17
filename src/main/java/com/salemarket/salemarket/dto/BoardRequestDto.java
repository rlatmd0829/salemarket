package com.salemarket.salemarket.dto;

import com.salemarket.salemarket.model.Board;
import com.salemarket.salemarket.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardRequestDto {
    //private Long id;
    private String title;
    private String content;

    private String category;
    private String region;

    private String imgUrl;
    private User user;

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .category(category)
                .region(region)
                .imgUrl(imgUrl)
                .user(user)
                .build();
    }

    @Builder
    public BoardRequestDto(String title, String content, String category, String region, String imgUrl, User user){
        this.title = title;
        this.content = content;
        this.category = category;
        this.region = region;
        this.imgUrl = imgUrl;
        this.user = user;
    }
}
