package com.declan.myboard.service;

import com.declan.myboard.domain.Board;
import com.declan.myboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public Page<Board> boardList(String title, String content, Pageable pageable) {
        return boardRepository.findByTitleContainingOrContentContaining(title, content, pageable);
    }

    @Transactional
    public void boardWrite(Board toEntity) {
        boardRepository.save(toEntity);
    }


    @Transactional(readOnly = true)
    public Board boardFindById(Long id) {
        return boardRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 아이디는 존재하지 않습니다. : " + id);
            }
        });

    }

    @Transactional
    public void boardUpdate(Board toEntity) {

        Board board = boardRepository.findById(toEntity.getId()).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 아이디는 존재하지 않습니다. : " + toEntity.getId());
            }
        });

        board.setTitle(toEntity.getTitle());
        board.setContent(toEntity.getContent());

    }


    @Transactional
    public void boardDelete(Long id) {

        boardRepository.deleteById(id);
    }
}
