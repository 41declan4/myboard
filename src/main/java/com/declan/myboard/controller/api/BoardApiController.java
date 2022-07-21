package com.declan.myboard.controller.api;

import com.declan.myboard.config.auth.PrincipalDetails;
import com.declan.myboard.domain.Board;
import com.declan.myboard.dto.BoardWriteDto;
import com.declan.myboard.dto.BoardupdateDto;
import com.declan.myboard.dto.ResponseDto;
import com.declan.myboard.repository.BoardRepository;
import com.declan.myboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/writeProc")
    public ResponseDto<Integer> write(@RequestBody BoardWriteDto boardWriteDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        boardService.boardWrite(boardWriteDto.toEntity(principalDetails));

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/updateProc/{id}")
    public ResponseDto<Integer> update(@RequestBody BoardupdateDto boardupdateDto, @PathVariable Long id) {

        boardService.boardUpdate(boardupdateDto.toEntity(id));

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

    }

    @DeleteMapping("/api/delete/{id}")
    public ResponseDto<Integer> delete(@PathVariable Long id) {
        boardService.boardDelete(id);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
