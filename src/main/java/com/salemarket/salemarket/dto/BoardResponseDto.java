package com.salemarket.salemarket.dto;

import com.salemarket.salemarket.model.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private String title;
    //private String imgUrl;
    private String region;
    private String category;

    public BoardResponseDto(Board board){
        this.title = board.getTitle();
        this.category = board.getCategory();
        this.region = board.getRegion();
    }

    @Builder
    public BoardResponseDto(String title, String category, String region){
        this.title = title;
        this.category = category;
        this.region = region;
    }

}
