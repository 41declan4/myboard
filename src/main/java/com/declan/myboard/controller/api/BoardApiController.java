package com.declan.myboard.controller.api;

import com.declan.myboard.config.auth.PrincipalDetails;
import com.declan.myboard.dto.BoardWriteDto;
import com.declan.myboard.dto.ResponseDto;
import com.declan.myboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

        private final BoardService boardService;

        @PostMapping("/api/writeProc")
        public ResponseDto<Integer> write(@RequestBody BoardWriteDto boardWriteDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {

            boardService.boardWrite(boardWriteDto.toEntity(principalDetails));

            return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
