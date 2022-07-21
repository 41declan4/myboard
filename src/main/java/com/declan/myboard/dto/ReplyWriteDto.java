package com.declan.myboard.dto;

import com.declan.myboard.domain.Reply;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReplyWriteDto {

    private Long userId;
    private Long boardId;
    private String comment;

}
