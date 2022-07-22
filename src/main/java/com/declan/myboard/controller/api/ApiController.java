package com.declan.myboard.controller.api;

import com.declan.myboard.domain.Board;
import com.declan.myboard.domain.Reply;
import com.declan.myboard.domain.User;
import com.declan.myboard.dto.ReplyWriteDto;
import com.declan.myboard.repository.BoardRepository;
import com.declan.myboard.repository.ReplyRepository;
import com.declan.myboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dummy/api")
public class ApiController {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final ReplyRepository replyRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    @GetMapping("/board/list")
    public List<Board> boardList() {
        return boardRepository.findAll();
    }

    @Transactional(readOnly = true)
    @GetMapping("/boards/title")
    public List<Board> boardSearchTitle(@RequestParam(defaultValue = "", required = false) String searchTitle) {
        return boardRepository.findByTitleContaining(searchTitle);
    }

    @Transactional(readOnly = true)
    @GetMapping("/boards/content")
    public List<Board> boardSearchContent(@RequestParam(defaultValue = "", required = false) String searchContent) {
        return boardRepository.findByContentContaining(searchContent);
    }

    @Transactional(readOnly = true)
    @GetMapping("/boards")
    public Page<Board> boardsPageAndSearch(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
                                        , @RequestParam(defaultValue = "", required = false) String searchText) {
        return boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
    }

    @Transactional(readOnly = true)
    @GetMapping("/board/{id}")
    public Board boardFindById(@PathVariable Long id) {
        Board board = boardRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 아이디는 존재하지 않습니다. " + id);
            }
        });

        return board;
    }

    @Transactional
    @PostMapping("/user/{id}/board/save")
    public Board boardSave(@RequestBody Board board, @PathVariable Long id) {

        User user = userRepository.findById(id).get();
        board.setUser(user);

        return boardRepository.save(board);
    }

    @Transactional
    @PutMapping("/board/{id}")
    public Board boardUpdate(@PathVariable Long id, @RequestBody Board reqBoard) {
        Board board = boardRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 아이디는 존재하지 않습니다. " + id);
            }
        });

        board.setTitle(reqBoard.getTitle());
        board.setContent(reqBoard.getContent());

        return board;
    }

    @Transactional
    @DeleteMapping("/board/{id}")
    public int boardDelete(@PathVariable Long id) {
        boardRepository.deleteById(id);

        return 1;
    }

    @Transactional(readOnly = true)
    @GetMapping("/user/list")
    public List<User> userList() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @GetMapping("/user/{id}")
    public User userFindById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 아이디는 찾을 수 없습니다. : " + id);
            }
        });
    }

    @Transactional(readOnly = true)
    @GetMapping("/user")
    public User userFindByUsername(@RequestParam(required = false, defaultValue = "") String username) {
        return userRepository.findByUsername(username).orElseThrow(new Supplier<UsernameNotFoundException>() {
            @Override
            public UsernameNotFoundException get() {
                return new UsernameNotFoundException("해당 유저는 찾을 수 없습니다. : " + username);
            }
        });
    }

    @Transactional
    @PostMapping("/user/signin")
    public User userSignin(@RequestBody User user) {
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }

    @Transactional
    @PutMapping("/user/{id}/update")
    public User userUpdate(@PathVariable Long id, @RequestBody User of) {
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 찾을 수 없습니다. : " + id);
            }
        });

        String rawPassword = of.getPassword();
        String encPassword= bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setEmail(of.getEmail());

        return user;
    }

    @Transactional
    @DeleteMapping("/user/{id}/delete")
    public int userDelete(@PathVariable Long id) {
        userRepository.deleteById(id);

        return 1;
    }

    @Transactional
    @PostMapping("/user/{userId}/board/{boardId}/reply/save")
    public String replySave(@PathVariable Long userId, @PathVariable Long boardId, @RequestBody ReplyWriteDto replyWriteDto) {

        replyRepository.mSave(userId, boardId, replyWriteDto.getComment());

        return "유저" + userId + "번 님꼐서 " + boardId + "번째의 글에 댓글을 작성하였습니다.";
    }

    @Transactional
    @DeleteMapping("/board/{boardId}/reply/{replyId}/delete")
    public String replyDelete(@PathVariable Long boardId, @PathVariable Long replyId) {

        replyRepository.deleteById(replyId);

        List<Reply> replys = replyRepository.findAll();


        for (int i = 0; i < replys.size(); i++) {

            if (replyId == replys.get(i).getId()) {
                return "" + boardId + "번째 게시글의 " + replyId + "번째 댓글을 삭제했습니다.";
            }
        }

        return "해당 댓글 번호는 존재하지 않습니다.";

    }


    public User of(String password, String email) {
        return User.builder()
                .password(password)
                .email(email)
                .build();

    }


}
