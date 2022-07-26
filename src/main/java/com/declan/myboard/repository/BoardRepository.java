package com.declan.myboard.repository;

import com.declan.myboard.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitleContaining(String title);
    List<Board> findByContentContaining(String searchContent);
    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
