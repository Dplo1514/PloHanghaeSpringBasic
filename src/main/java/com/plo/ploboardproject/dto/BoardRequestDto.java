package com.plo.ploboardproject.dto;

import com.plo.ploboardproject.domain.Board;
import lombok.*;
import org.springframework.context.annotation.Configuration;


@Configuration
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequestDto {
    private String boardTitle;
    private String boardContents;
    private String userName;


    public Board toEntity() {
        Board boards = Board.builder()
                .boardTitle(boardTitle)
                .boardContents(boardContents)
                .userName(userName)
                .build();
        return boards;
    }
}

