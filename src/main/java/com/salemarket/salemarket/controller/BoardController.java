package com.salemarket.salemarket.controller;

import com.salemarket.salemarket.dto.BoardRequestDto;
import com.salemarket.salemarket.dto.BoardResponseDto;
import com.salemarket.salemarket.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/boards")
    public List<BoardResponseDto> getBoards(){
        List<BoardResponseDto> boards = boardService.getBoards();
        return boards;
    }

    @PostMapping("/boards")
    public ResponseEntity saveBoard(@RequestBody BoardRequestDto boardRequestDto){
        boardService.addBoard(boardRequestDto);
        return new ResponseEntity("게시글을 저장하였습니다.", HttpStatus.OK);
    }

    @PutMapping("/boards/{boardId}")
    public ResponseEntity updateBoard(@PathVariable Long boardId, @RequestBody BoardRequestDto boardRequestDto){
        boardService.updateBoard(boardId, boardRequestDto);
        return new ResponseEntity("게시글을 수정하였습니다.", HttpStatus.OK);
    }

    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity deleteBoard(@PathVariable Long boardId){
        boardService.deleteBoard(boardId);
        return new ResponseEntity("게시글을 삭제하였습니다.", HttpStatus.OK);
    }
}
