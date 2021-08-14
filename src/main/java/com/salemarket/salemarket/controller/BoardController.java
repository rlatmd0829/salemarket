package com.salemarket.salemarket.controller;

import com.salemarket.salemarket.config.UserDetailsImpl;
import com.salemarket.salemarket.config.S3Uploader;
import com.salemarket.salemarket.dto.BoardDetailResponseDto;
import com.salemarket.salemarket.dto.BoardRequestDto;
import com.salemarket.salemarket.dto.BoardResponseDto;
import com.salemarket.salemarket.model.User;
import com.salemarket.salemarket.repository.UserRepository;
import com.salemarket.salemarket.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final S3Uploader s3Uploder;
    private final UserRepository userRepository;

    @GetMapping("/boards")
    public List<BoardResponseDto> getBoard(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        System.out.println(userDetailsImpl);
        if (userDetailsImpl == null){
            return boardService.getBoard();
        }else{
            return boardService.getBoard(userDetailsImpl.getId());
        }

    }


    @PostMapping("/boards")
    public ResponseEntity saveBoard(@RequestParam("title") String title, @RequestParam("content") String content,
                                    @RequestParam("category") String category, @RequestParam("region") String region, @RequestParam("file")MultipartFile files,
                                    @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) throws IOException {
        String imgUrl = s3Uploder.upload(files, "board");
        User user = userRepository.findByUsername(userDetailsImpl.getUsername());
        BoardRequestDto boardRequestDto = new BoardRequestDto(title, content, category, region, imgUrl, user);

        boardService.saveBoard(boardRequestDto);
        return new ResponseEntity("게시글을 저장하였습니다.", HttpStatus.OK);
    }

    @PutMapping("/boards/{boardId}")
    public ResponseEntity updateBoard(@PathVariable Long boardId,@RequestParam("title") String title, @RequestParam("content") String content,
                                      @RequestParam("category") String category, @RequestParam("region") String region,
                                      @RequestParam(value = "file", required = false) MultipartFile files, @RequestParam(value = "imgUrl", required = false) String imgUrl,
                                      @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) throws IOException {

        // 이미지 수정없이 게시글 수정할 때는 s3에 업로드 할 필요 없으므로 imgUrl이 안넘어 올 경우에만 업로드를 시켜준다.
        if(imgUrl == null) {
            imgUrl = s3Uploder.upload(files, "board");
        }
        User user = userRepository.findByUsername(userDetailsImpl.getUsername());
        BoardRequestDto boardRequestDto = new BoardRequestDto(title, content, category, region, imgUrl, user);
        BoardRequestDto updateBoard = boardService.updateBoard(boardId, boardRequestDto, userDetailsImpl.getId());
        if(updateBoard != null){
            return new ResponseEntity("게시글을 수정하였습니다.", HttpStatus.OK);
        }
        else{
            return new ResponseEntity("게시글을 수정할 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity deleteBoard(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        boolean check = boardService.deleteBoard(boardId, userDetailsImpl.getId());
        if(check){
            return new ResponseEntity("게시글을 삭제하였습니다.", HttpStatus.OK);
        }
        else{
            return new ResponseEntity("게시글을 삭제할 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // 좋아요를 board쪽에서 update함수로 갱신하면 반드시 새로고침이 들어가야함 바뀌기 때문에 다른 방법 생각해보기
    @GetMapping("/boards/{boardId}/details")
    public BoardDetailResponseDto detailsBoard(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        if (userDetailsImpl == null){
            return boardService.detail(boardId);
        }
        else{
            return boardService.detail(boardId, userDetailsImpl.getId());
        }

    }
}
