package com.salemarket.salemarket.controller;

import com.salemarket.salemarket.config.UserDetailsImpl;
import com.salemarket.salemarket.dto.CommentRequestDto;
import com.salemarket.salemarket.dto.CommentResponseDto;
import com.salemarket.salemarket.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity saveComment(@PathVariable Long boardId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        commentService.saveComment(boardId, commentRequestDto, userDetailsImpl.getId());
        return new ResponseEntity("댓글을 작성하였습니다.", HttpStatus.OK);
    }

    @PutMapping("/boards/{boardId}/comments/{commentId}")
    public ResponseEntity updateComment(@PathVariable Long boardId, @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto,
                                        @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        CommentRequestDto updateComment = commentService.updateComment(boardId, commentId, commentRequestDto, userDetailsImpl.getId());
        if(updateComment != null){
            return new ResponseEntity("댓글을 수정하였습니다.", HttpStatus.OK);
        }
        else{
            return new ResponseEntity("댓글을 수정할 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/boards/{boardId}/comments/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Long boardId, @PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        boolean check = commentService.deleteComment(boardId, commentId, userDetailsImpl.getId());
        if(check){
            return new ResponseEntity("댓글을 삭제하였습니다.", HttpStatus.OK);
        }else{
            return new ResponseEntity("댓글을 삭제할 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
