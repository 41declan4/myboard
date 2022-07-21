package com.declan.myboard.controller;

import com.declan.myboard.domain.Board;
import com.declan.myboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping({"", "/"})
    public String index(Model model
            , @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            , @RequestParam(defaultValue = "", required = false) String searchText) {
        Page<Board> boards = boardService.boardList(searchText, searchText, pageable);

        model.addAttribute("boards", boards.getContent());
        model.addAttribute("number", boards.getNumber());
        model.addAttribute("totalPages", boards.getTotalPages());
        model.addAttribute("totalElements", boards.getTotalElements());
        model.addAttribute("size", boards.getSize());
        model.addAttribute("isFirst", boards.isFirst());
        model.addAttribute("hasNext", boards.hasNext());

        return "index";
    }

    @GetMapping("/board/write")
    public String boardFrom() {
        return "board/boardWrite";
    }
}
