package com.declan.myboard.service;

import com.declan.myboard.domain.Board;
import com.declan.myboard.domain.User;
import com.declan.myboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void userJoin(User userJoinDto) {
        String rawPwd = userJoinDto.getPassword();
        String encPwd = bCryptPasswordEncoder.encode(rawPwd);

        userJoinDto.setPassword(encPwd);

        userRepository.save(userJoinDto);
    }
}
