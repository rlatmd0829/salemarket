package com.salemarket.salemarket.model;

import com.salemarket.salemarket.dto.BoardDetailResponseDto;
import com.salemarket.salemarket.dto.BoardRequestDto;
import com.salemarket.salemarket.dto.BoardResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "BOARD_ID")
    private Long id;
    private String title;
    private String content;
    private String category;
    private String region;
    private String imgUrl;
    private Integer heartCount;
    private boolean heartCheck;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Heart> likes = new ArrayList<>();

    @Builder
    public Board(String title, String content, String category, String region, String imgUrl, User user){
        this.title = title;
        this.content = content;
        this.category = category;
        this.region = region;
        this.imgUrl = imgUrl;
        this.user = user;
    }

    public BoardResponseDto toDto(){
        return BoardResponseDto.builder()
                .boardId(id)
                .title(title)
                .category(category)
                .region(region)
                .imgUrl(imgUrl)
                .user(user)
                .createdAt(getCreatedAt())
                .heartCount(heartCount)
                .heartCheck(heartCheck)
                .build();
    }

    public BoardDetailResponseDto toDto2(){
        return BoardDetailResponseDto.builder()
                .boardId(id)
                .title(title)
                .content(content)
                .category(category)
                .region(region)
                .imgUrl(imgUrl)
                .user(user)
                .createdAt(getCreatedAt())
                .heartCount(heartCount)
                .heartCheck(heartCheck)
                .build();
    }

    public void update(BoardRequestDto boardRequestDto){
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.category = boardRequestDto.getCategory();
        this.region = boardRequestDto.getRegion();
        this.imgUrl = boardRequestDto.getImgUrl();
    }

    public void heartUpdate(boolean heartCheck, Integer heartCount){
        this.heartCheck = heartCheck;
        this.heartCount = heartCount;
    }

//    public void heartPlus(){
//        heartCheck = true;
//        heartCount++;
//    }
//
//    public void heartMinus(){
//        heartCheck = false;
//        heartCount--;
//    }
}
