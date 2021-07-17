package com.salemarket.salemarket.dto;

import com.salemarket.salemarket.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long boardId;
    private String title;

    private String region;
    private String category;
    private String imgUrl;
    private User user;

//    public BoardResponseDto(Board board){
//        this.id = board.getId();
//        this.title = board.getTitle();
//        this.category = board.getCategory();
//        this.region = board.getRegion();
//    }

    @Builder
    public BoardResponseDto(Long boardId, String title, String category, String region, String imgUrl, User user){
        this.boardId = boardId;
        this.title = title;
        this.category = category;
        this.region = region;
        this.imgUrl = imgUrl;
        this.user = user;
    }

}
