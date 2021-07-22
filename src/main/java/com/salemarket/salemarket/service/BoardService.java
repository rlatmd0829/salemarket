package com.salemarket.salemarket.service;

import com.salemarket.salemarket.dto.BoardRequestDto;
import com.salemarket.salemarket.dto.BoardResponseDto;
import com.salemarket.salemarket.model.Board;
import com.salemarket.salemarket.model.User;
import com.salemarket.salemarket.repository.BoardRepository;
import com.salemarket.salemarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public List<BoardResponseDto> getBoard(){
        List<Board> boardAll = boardRepository.findAll();

        // stream을 이용하여 for문 대신 사용가능
        // 여기서 board는 boardAll.stream()을 사용하면 안에 원소값을 한개씩 나오는데 그게 board이다.
        // 하나씩 나온 board를 BoardResponseDto로 변환 시켜준후 List로 만들어준다.
        // boardResponseDto로 변환시키기 위해서 생성자 대신해서 Builder 패턴을 사용해보았다.

        return boardAll.stream().map(board -> board.toDto()).collect(Collectors.toList());

    }

    public void saveBoard(BoardRequestDto boardRequestDto) {

        boardRepository.save(boardRequestDto.toEntity());

    }

    @Transactional
    public BoardRequestDto updateBoard(Long boardId, BoardRequestDto boardRequestDto, Long userId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        // User 추가되면 게시글 작성 User랑 현재 로그인한 User가 같을 경우만 수정 가능하게 변경해야함 // 완료
        if(board.getUser().getId().equals(userId)){
            board.update(boardRequestDto);
            return boardRequestDto;
        }
        else{
            return null;
        }

    }


    // 댓글있는 게시글 삭제시 삭제가 안되었다.
    // => board와 comment 양방향 매핑 후 cascade ALL 걸어주니까 댓글이 있는 게시글이라도 삭제가 되었다.
    public boolean deleteBoard(Long boardId, Long userId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(board.getUser().getId().equals(userId)){
            boardRepository.deleteById(boardId);
            return true;
        }
        else{
            return false;
        }
    }
}
