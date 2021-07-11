package com.salemarket.salemarket.controller;

import com.salemarket.salemarket.config.S3Uploader;
import com.salemarket.salemarket.dto.BoardRequestDto;
import com.salemarket.salemarket.dto.BoardResponseDto;
import com.salemarket.salemarket.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final S3Uploader s3Uploder;

    @GetMapping("/boards")
    public List<BoardResponseDto> getBoard(){
        return boardService.getBoard();
    }

//    @PostMapping("/boards")
//    public ResponseEntity saveBoard(@RequestBody BoardRequestDto boardRequestDto){
//        boardService.saveBoard(boardRequestDto);
//        return new ResponseEntity("게시글을 저장하였습니다.", HttpStatus.OK);
//    }

    @PostMapping("/boards")
    public ResponseEntity saveBoard(@RequestParam("title") String title, @RequestParam("content") String content,
                                    @RequestParam("category") String category, @RequestParam("region") String region, @RequestParam("file")MultipartFile files) throws IOException {
        String imgUrl = s3Uploder.upload(files, "board");

        BoardRequestDto boardRequestDto = new BoardRequestDto(title, content, category, region, imgUrl);

        boardService.saveBoard(boardRequestDto);
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
