package com.salemarket.salemarket.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Heart {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "USER_ID")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "BOARD_ID")
//    private Board board;

}
