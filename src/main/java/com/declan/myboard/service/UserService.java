package com.declan.myboard.service;

import com.declan.myboard.domain.Board;
import com.declan.myboard.domain.User;
import com.declan.myboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

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

    @Transactional
    public void userUpdate(User toEntity) {
        System.out.println("+++++ : " + toEntity.toString());

        User user = userRepository.findById(toEntity.getId()).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 아이디는 존재하지 않습니다. : " + toEntity.getId());
            }
        });

        System.out.println("=============== : " + user);

        String rawPwd = toEntity.getPassword();
        String encPwd = bCryptPasswordEncoder.encode(rawPwd);

        user.setPassword(encPwd);
        user.setEmail(toEntity.getEmail());


    }
}
