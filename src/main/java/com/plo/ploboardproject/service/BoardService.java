package com.plo.ploboardproject.service;

import com.plo.ploboardproject.domain.Board;
import com.plo.ploboardproject.domain.Comment;
import com.plo.ploboardproject.dto.CommentRequestDto;
import org.hibernate.service.NullServiceException;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private final CommentRepository commentRepository;
    private final CommentRequestDto requestDto;
    private final BoardRepository boardRepository;

    public BoardService(CommentRepository commentRepository, CommentRequestDto requestDto, BoardRepository boardRepository) {
        this.commentRepository = commentRepository;
        this.requestDto = requestDto;
        this.boardRepository = boardRepository;
    }

    public void CommentSave (String name , String comment , long id){

        Board board = boardRepository.getById(id);
        String username = name;
        String comments = comment;
        if (comments.equals("")){
            throw new IllegalArgumentException("공백은 입력할 수 없습니다.");
        }
        requestDto.setBoard(board);
        requestDto.setUser(username);
        requestDto.setComment(comments);

        Comment commentSave = requestDto.toEntity();
        commentRepository.save(commentSave);
    }
}
