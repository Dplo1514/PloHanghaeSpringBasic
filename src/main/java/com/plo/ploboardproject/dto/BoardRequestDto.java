package com.plo.ploboardproject.dto;

import lombok.Getter;

@Getter
public class BoardRequestDto {
    private final String boardTitle;
    private final String boardContents;
    private final String userName;

    public BoardRequestDto(String boardTitle, String boardContents, String userName) {
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
        this.userName = userName;
    }
}
