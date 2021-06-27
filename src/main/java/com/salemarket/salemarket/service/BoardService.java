package com.salemarket.salemarket.service;

import com.salemarket.salemarket.dto.BoardRequestDto;
import com.salemarket.salemarket.dto.BoardResponseDto;
import com.salemarket.salemarket.model.Board;
import com.salemarket.salemarket.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<BoardResponseDto> getBoards(){
        List<Board> boardAll = boardRepository.findAll();
        List<BoardResponseDto> boardResponseDtos = boardAll.stream().map(board -> board.toDto()).collect(Collectors.toList());

        // stream을 이용하여 for문 대신 사용가능
        // 여기서 board는 boardAll.stream()을 사용하면 안에 원소값을 한개씩 나오는데 그게 board이다.
        // 하나씩 나온 board를 BoardResponseDto로 변환 시켜준후 List로 만들어준다.
        // boardResponseDto로 변환시키기 위해서 생성자 대신해서 Builder 패턴을 사용해보았다.

        return boardResponseDtos;

    }

    public void addBoard(BoardRequestDto boardRequestDto) {

        boardRepository.save(boardRequestDto.toEntity());

    }

    @Transactional
    public void updateBoard(Long boardId, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        // User 추가되면 게시글 작성 User랑 현재 로그인한 User가 같을 경우만 수정 가능하게 변경해야함
        board.update(boardRequestDto);
    }


    public void deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        boardRepository.deleteById(boardId);
    }
}
