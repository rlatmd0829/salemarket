package com.salemarket.salemarket.dto;

import com.salemarket.salemarket.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardDetailResponseDto {
    private Long boardId;
    private String title;
    private String content;
    private String region;
    private String category;
    private String imgUrl;
    private LocalDateTime createdAt;
    private Integer heartCount;
    private boolean heartCheck;
    private User user;

    @Builder
    public BoardDetailResponseDto(Long boardId, String title, String content, String category, String region, String imgUrl, LocalDateTime createdAt
            ,Integer heartCount, boolean heartCheck, User user){
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.category = category;
        this.region = region;
        this.imgUrl = imgUrl;
        this.createdAt = createdAt;
        this.heartCount = heartCount;
        this.heartCheck = heartCheck;
        this.user = user;
    }
}
