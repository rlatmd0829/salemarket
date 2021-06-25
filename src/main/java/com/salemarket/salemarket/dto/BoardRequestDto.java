package com.salemarket.salemarket.dto;

import com.salemarket.salemarket.model.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardRequestDto {
    //private Long id;
    private String title;
    private String content;
    //private String imgUrl;
    private String category;
    private String region;
    //private User user;

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .category(category)
                .region(region)
                .build();
    }
}
