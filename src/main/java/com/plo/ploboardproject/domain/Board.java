package com.plo.ploboardproject.domain;

import com.plo.ploboardproject.dto.BoardRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name="board") //엔티티와 관련을 맺고 있는 테이블이 board 테이블이라고 명시
public class Board extends TimeStamped{
    @GeneratedValue(strategy = GenerationType.AUTO) //게시글이 생성됨에 따라 자동으로 갯수를 늘려줍니다.
    @Id
    private long id;

    @Column(nullable = false)
    private String boardTitle;

    @Column(nullable = false)
    private String boardContents;

    @Column(nullable = false)
    private String userName;


    public Board(BoardRequestDto requestDto){
        this.boardTitle = requestDto.getBoardTitle();
        this.boardContents = requestDto.getBoardContents();
        this.userName = requestDto.getUserName();
    }

    public Board(String boardTitle , String boardContents, String userName){
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
        this.userName = userName;
    }

}
