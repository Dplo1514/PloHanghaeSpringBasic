package com.plo.ploboardproject.domain;

import com.plo.ploboardproject.dto.BoardWriteRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class BoardWrite extends TimeStamped{
    @GeneratedValue(strategy = GenerationType.AUTO) //게시글이 생성됨에 따라 자동으로 갯수를 늘려줍니다.
    @Id
    private long id;

    @Column(nullable = false)
    private String boardTitle;

    @Column(nullable = false)
    private String boardContents;

    @Column(nullable = false)
    private String userName;


    public BoardWrite(BoardWriteRequestDto requestDto){
        this.boardTitle = requestDto.getBoardTitle();
        this.boardContents = requestDto.getBoardContents();
        this.userName = requestDto.getUserName();
    }

    public BoardWrite(String boardTitle , String boardContents, String userName){
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
        this.userName = userName;
    }

}
