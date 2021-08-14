package com.salemarket.salemarket.repository;

import com.salemarket.salemarket.model.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Heart findByBoardIdAndUserId(Long boardId, Long userId);

    List<Heart> findByBoardId(Long boardId);

    boolean existsByBoardIdAndUserId(Long id, Long userId);
}
