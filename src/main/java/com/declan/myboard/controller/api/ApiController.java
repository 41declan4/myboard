package com.declan.myboard.controller.api;

import com.declan.myboard.domain.Board;
import com.declan.myboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dummy/api")
public class ApiController {

    private final BoardRepository boardRepository;

    @GetMapping("/boardList")
    public List<Board> boardList() {
        return boardRepository.findAll();
    }

    @PostMapping("/boardSave")
    public Board boardSave(@RequestBody Board board) {
        return boardRepository.save(board);
    }


}
