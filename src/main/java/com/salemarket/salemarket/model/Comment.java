package com.salemarket.salemarket.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

//    @ManyToOne
//    @JoinColumn(name = "USER_ID")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "BOARD_ID")
//    private Board board;
}
