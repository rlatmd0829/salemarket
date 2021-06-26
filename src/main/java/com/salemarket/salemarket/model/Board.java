package com.salemarket.salemarket.model;

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
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "BOARD_ID")
    private Long id;
    private String title;
    private String content;
    //private String imgUrl;
    private String category;
    private String region;

//    @ManyToOne
//    @JoinColumn(name = "USER_ID")
//    private User user;
//
//    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Comment> comments = new ArrayList<>();
//
//    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<like> likes = new ArrayList<>();

    @Builder
    public Board(String title, String content, String category, String region){
        this.title = title;
        this.content = content;
        this.category = category;
        this.region = region;
    }

    public BoardResponseDto toDto(){
        return BoardResponseDto.builder()
                .title(title)
                .category(category)
                .region(region)
                .build();
    }
}
