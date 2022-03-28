package com.plo.ploboardproject.dto;

import com.plo.ploboardproject.domain.Board;
import com.plo.ploboardproject.domain.Comment;
import lombok.Getter;

import java.util.List;

@Getter
public class BoardRequestDto {
    private final String boardTitle;
    private final String boardContents;
    private final String userName;
    private final List<Comment> comment;
    private final Board board;

    public BoardRequestDto(String boardTitle, String boardContents, String userName, List<Comment> comment, Board board) {
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
        this.userName = userName;
        this.board = board;
        this.comment = comment;
    }
}

