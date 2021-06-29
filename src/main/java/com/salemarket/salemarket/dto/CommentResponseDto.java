package com.salemarket.salemarket.dto;

import com.salemarket.salemarket.model.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private String content;
    private Board board;

    @Builder
    public CommentResponseDto(Long commentId, String content, Board board){
        this.commentId = commentId;
        this.content = content;
        this.board = board;
    }
}
