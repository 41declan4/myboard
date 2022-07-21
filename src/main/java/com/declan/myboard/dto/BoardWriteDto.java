package com.declan.myboard.dto;

import com.declan.myboard.config.auth.PrincipalDetails;
import com.declan.myboard.domain.Board;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Data
@Builder
public class BoardWriteDto {

    private String title;
    private String content;

    public Board toEntity(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return Board.builder()
                .title(title)
                .content(content)
                .count(0)
                .user(principalDetails.getUser())
                .build();
    }
}
