package com.declan.myboard.controller.api;

import com.declan.myboard.domain.Reply;
import com.declan.myboard.dto.ReplyWriteDto;
import com.declan.myboard.dto.ResponseDto;
import com.declan.myboard.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReplyApiController {

    private final ReplyService replyService;

    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> write(@RequestBody ReplyWriteDto replyWriteDto) {

        replyService.commentSave(replyWriteDto);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

    }

    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> delete(@PathVariable Long replyId) {
        replyService.commentDelete(replyId);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
