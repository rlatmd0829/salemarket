package com.salemarket.salemarket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String title;
    //private String imgUrl;
    private String region;
    private String category;

//    public BoardResponseDto(Board board){
//        this.id = board.getId();
//        this.title = board.getTitle();
//        this.category = board.getCategory();
//        this.region = board.getRegion();
//    }

    @Builder
    public BoardResponseDto(Long id, String title, String category, String region){
        this.id = id;
        this.title = title;
        this.category = category;
        this.region = region;
    }

}
