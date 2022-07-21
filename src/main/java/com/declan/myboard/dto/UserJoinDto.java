package com.declan.myboard.dto;

import com.declan.myboard.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserJoinDto {

    private String username;
    private String password;
    private String email;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .role("ROLE_USER")
                .build();
    }
}
