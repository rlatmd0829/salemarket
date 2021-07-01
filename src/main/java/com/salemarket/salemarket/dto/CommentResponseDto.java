package com.salemarket.salemarket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private String content;
    private Long boardId;

    @Builder
    public CommentResponseDto(Long commentId, String content, Long boardId){
        this.commentId = commentId;
        this.content = content;
        this.boardId = boardId;
    }
}
