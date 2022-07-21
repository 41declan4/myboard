package com.declan.myboard.dto;

import com.declan.myboard.config.auth.PrincipalDetails;
import com.declan.myboard.domain.Board;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;

@Data
@Builder
public class BoardupdateDto {

    private Long id;

    private String title;
    private String content;

    public Board toEntity(@PathVariable Long id) {
        return Board.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
    }
}
