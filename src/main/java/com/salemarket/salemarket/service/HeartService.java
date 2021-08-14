package com.salemarket.salemarket.service;

import com.salemarket.salemarket.model.Board;
import com.salemarket.salemarket.model.Heart;
import com.salemarket.salemarket.model.User;
import com.salemarket.salemarket.repository.BoardRepository;
import com.salemarket.salemarket.repository.HeartRepository;
import com.salemarket.salemarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public HashMap<String, Object> getHeart(Long boardId, Long userId) {
        Heart heart = heartRepository.findByBoardIdAndUserId(boardId, userId);

        // 게시글 좋아요 갯수
        List<Heart> heartCount = heartRepository.findByBoardId(boardId);
        Integer Count = heartCount.size();
        HashMap<String,Object> hashMap = new HashMap<>();

        if(heart == null){
            hashMap.put("check", false);
            hashMap.put("heartCount", Count);
            return hashMap;
        }else{
            hashMap.put("check", true);
            hashMap.put("heartCount", Count);
            return hashMap;
        }
    }

    public Heart postHeart(Long boardId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("계정이 존재하지 않습니다.")
        );
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        Heart heart = heartRepository.findByBoardIdAndUserId(boardId, userId);
        if(heart == null){
            Heart newHeart = new Heart(user, board);
            //board.heartPlus();
            heartRepository.save(newHeart);
            return newHeart;
        }else{
            return null;
        }
    }

    public Heart deleteHeart(Long boardId, Long userId) {
        Heart heart = heartRepository.findByBoardIdAndUserId(boardId, userId);
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        if(heart == null){
            return null;
        }else{
            heartRepository.deleteById(heart.getId());
            //board.heartMinus();
            return heart;
        }
    }
}
