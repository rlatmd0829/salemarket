package com.salemarket.salemarket.service;

import com.salemarket.salemarket.dto.CommentRequestDto;
import com.salemarket.salemarket.dto.CommentResponseDto;
import com.salemarket.salemarket.model.Board;
import com.salemarket.salemarket.model.Comment;
import com.salemarket.salemarket.repository.BoardRepository;
import com.salemarket.salemarket.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public List<CommentResponseDto> getComment(Long boardId) {
        List<Comment> comments = commentRepository.findByBoardId(boardId);
        return comments.stream().map(comment -> comment.toDto()).collect(Collectors.toList());
    }

    public void saveComment(Long boardId, CommentRequestDto commentRequestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        Comment comment = commentRequestDto.toEntity();
        comment.addBoard(board);
        commentRepository.save(comment);

        // comment 객체안에 board를 넣어서 프론트에 보내주는게 맞나?? 일단 넣어놓긴함

    }
}
