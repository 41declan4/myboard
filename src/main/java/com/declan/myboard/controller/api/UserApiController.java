package com.declan.myboard.controller.api;

import com.declan.myboard.domain.User;
import com.declan.myboard.dto.ResponseDto;
import com.declan.myboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> join(@RequestBody User user) {
        userService.userJoin(user);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}