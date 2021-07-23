package com.salemarket.salemarket.controller;

import com.salemarket.salemarket.config.UserDetailsImpl;
import com.salemarket.salemarket.model.Heart;
import com.salemarket.salemarket.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;

    @GetMapping("/boards/{boardId}/heart")
    public HashMap<String, Object> getHeart(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        return heartService.getHeart(boardId, userDetailsImpl.getId());
    }

    @PostMapping("/boards/{boardId}/heart")
    public ResponseEntity postHeart(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        Heart heart = heartService.postHeart(boardId, userDetailsImpl.getId());
        if(heart == null){
            return new ResponseEntity("이미 좋아요 상태입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else{
            return new ResponseEntity("좋아요 성공.", HttpStatus.OK);
        }
    }

    @DeleteMapping("/boards/{boardId}/heart")
    public ResponseEntity deleteHeart(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        Heart heart = heartService.deleteHeart(boardId, userDetailsImpl.getId());
        if(heart == null){
            return new ResponseEntity("취소할 좋아요가 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else{
            return new ResponseEntity("좋아요 취소 성공.", HttpStatus.OK);
        }
    }

}
