package com.salemarket.salemarket.repository;

import com.salemarket.salemarket.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByModifiedAtDesc();

    List<Board> findAllByOrderByCreatedAtDesc();
}
