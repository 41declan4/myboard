package com.declan.myboard.controller.api;

import com.declan.myboard.dto.ResponseDto;
import com.declan.myboard.dto.UserJoinDto;
import com.declan.myboard.dto.UserUpdateDto;
import com.declan.myboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> join(@RequestBody UserJoinDto userJoinDto) {
        userService.userJoin(userJoinDto.toEntity());

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/user/updateProc/{id}")
    public ResponseDto<Integer> update(@RequestBody UserUpdateDto userUpdateDto) {
        System.out.println(userUpdateDto);
        userService.userUpdate(userUpdateDto.toEntity());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userUpdateDto.getUsername(), userUpdateDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);


        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
