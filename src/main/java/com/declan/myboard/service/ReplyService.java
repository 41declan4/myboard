package com.declan.myboard.service;

import com.declan.myboard.domain.Reply;
import com.declan.myboard.dto.ReplyWriteDto;
import com.declan.myboard.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    @Transactional
    public void commentSave(ReplyWriteDto replyWriteDto) {
        replyRepository.mSave(replyWriteDto.getUserId(), replyWriteDto.getBoardId(), replyWriteDto.getComment());
    }

    @Transactional
    public void commentDelete(Long replyId) {
        replyRepository.deleteById(replyId);
    }
}
