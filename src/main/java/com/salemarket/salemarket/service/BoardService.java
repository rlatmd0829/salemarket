package com.salemarket.salemarket.service;

import com.salemarket.salemarket.dto.BoardRequestDto;
import com.salemarket.salemarket.dto.BoardResponseDto;
import com.salemarket.salemarket.model.Board;
import com.salemarket.salemarket.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final ModelMapper mapper;

    public List<Board> getBoards(){
        List<Board> boardAll = boardRepository.findAll();
        return boardAll;

        // entity 그대로 받아서 넣는중 dto로 변경하는법 찾아야함...
        // 특히 List엔티티 ListDTo 서로 변환 하는법..
    }

    public void addBoard(Board board) {
        //Board board = mapper.map(boardRequestDto, Board.class);
        boardRepository.save(board);
        //boardRepository.save(boardRequestDto.toEntity());
        //boardRequestDto.toEntity()

        // entity 그대로 받아서 넣는중 dto로 변경하는법 찾아야함...
        // (Builder, Modelmapper 둘다 안되긴했음..)
    }
}
