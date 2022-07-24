package com.declan.myboard.dto;

import com.declan.myboard.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateDto {

    private Long id;
    private String username;
    private String password;
    private String email;

    public User toEntity() {
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .email(email)
                .build();
    }
}
