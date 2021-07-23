package com.salemarket.salemarket.model;

import com.salemarket.salemarket.dto.CommentRequestDto;
import com.salemarket.salemarket.dto.CommentResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    public CommentResponseDto toDto() {
        return CommentResponseDto.builder()
                .commentId(id)
                .content(content)
                .boardId(board.getId())
                .build();
    }
    @Builder
    public Comment(String content, User user){
        this.content = content;
        this.user = user;
    }

    public void addBoard(Board board) {
        this.board = board;
    }
    public void addUser(User user){
        this.user = user;
    }

    public void update(CommentRequestDto commentRequestDto){
        content = commentRequestDto.getContent();
    }



}
