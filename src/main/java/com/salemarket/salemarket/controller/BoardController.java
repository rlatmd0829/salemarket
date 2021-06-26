package com.salemarket.salemarket.controller;

import com.salemarket.salemarket.dto.BoardRequestDto;
import com.salemarket.salemarket.dto.BoardResponseDto;
import com.salemarket.salemarket.model.Board;
import com.salemarket.salemarket.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public void saveBoard(@RequestBody BoardRequestDto boardRequestDto){
        boardService.addBoard(boardRequestDto);
    }
}
