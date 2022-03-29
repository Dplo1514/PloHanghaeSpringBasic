package com.plo.ploboardproject;

import com.plo.ploboardproject.dto.CommentRequestDto;
import com.plo.ploboardproject.service.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PloBoardProjectApplicationTestsWrite {

    private final CommentRepository commentRepository;

    PloBoardProjectApplicationTestsWrite(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Test
    void contextLoads() {
        CommentRequestDto commentRequestDto = new CommentRequestDto();
        commentRequestDto = (CommentRequestDto) commentRepository.findByBoard_idOrderByModifiedAtDesc(175);
        System.out.println(commentRequestDto.getUser());
    }

}
