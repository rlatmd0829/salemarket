package com.salemarket.salemarket.controller;

import com.salemarket.salemarket.dto.CommentRequestDto;
import com.salemarket.salemarket.dto.CommentResponseDto;
import com.salemarket.salemarket.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommnetController {

    private final CommentService commentService;

    @GetMapping("/boards/{boardId}/comments")
    public List<CommentResponseDto> getComment(@PathVariable Long boardId){
        return commentService.getComment(boardId);
    }

    @PostMapping("/boards/{boardId}/comments")
    public ResponseEntity saveComment(@PathVariable Long boardId, @RequestBody CommentRequestDto commentRequestDto){
        commentService.saveComment(boardId, commentRequestDto);
        return new ResponseEntity("댓글을 작성하였습니다.", HttpStatus.OK);
    }
}
