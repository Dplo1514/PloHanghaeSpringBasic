package com.plo.ploboardproject.service;

import com.plo.ploboardproject.domain.Board;
import com.plo.ploboardproject.domain.Comment;
import com.plo.ploboardproject.dto.BoardRequestDto;
import com.plo.ploboardproject.dto.CommentRequestDto;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private final CommentRepository commentRepository;
    private final CommentRequestDto requestDto;
    private final BoardRepository boardRepository;
    private final BoardRequestDto boardRequestDto;

    public BoardService(CommentRepository commentRepository, CommentRequestDto requestDto, BoardRepository boardRepository, BoardRequestDto boardRequestDto) {
        this.commentRepository = commentRepository;
        this.requestDto = requestDto;
        this.boardRepository = boardRepository;
        this.boardRequestDto = boardRequestDto;
    }

    public void BoardSave(String name, String title, String contents) {
        String username = name;
        String boardTitle = title;
        String boardContents = contents;
        boardRequestDto.setUserName(username);
        boardRequestDto.setBoardTitle(boardTitle);
        boardRequestDto.setBoardContents(boardContents);

        if (title.equals("")) {
            throw new IllegalArgumentException("공백은 입력할 수 없습니다.");
        }

        if (contents.equals("")) {
            throw new IllegalArgumentException("공백은 입력할 수 없습니다.");
        }

        Board boardSave = boardRequestDto.toEntity();
        boardRepository.save(boardSave);
    }

    public void CommentSave(String name, String comment, long id) {

        Board board = boardRepository.getById(id);
        String username = name;
        String comments = comment;
        if (comments.equals("")) {
            throw new IllegalArgumentException("공백은 입력할 수 없습니다.");
        }
        requestDto.setBoard(board);
        requestDto.setUser(username);
        requestDto.setComment(comments);

        Comment commentSave = requestDto.toEntity();
        commentRepository.save(commentSave);
    }
}
