package com.plo.ploboardproject.domain;
import com.plo.ploboardproject.dto.BoardRequestDto;
import com.plo.ploboardproject.dto.CommentRequestDto;
import lombok.*;

import javax.persistence.*;
import javax.websocket.server.ServerEndpoint;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "comment")
public class Comment extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String user;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment; // 댓글 내용

    //comment는 Board와 manytoone관계를 갖게된다.
    @ManyToOne
    //@JoinColumn : FK를 가지는 엔티티가 @JoinColumn 어노테이션을 사용합니다.
    //논리적으로 Comment가 어떤 카테고리에 속하는지를 식별해야하기 때문에 Comment가 FK를 가집니다.
    //name="board_id" : 이것이 실제 Board 테이블에 있는 board테이블의 FK 컬럼명이 된다.
    @JoinColumn(name = "board_id")
    private Board board;

    public Comment(String user , String comment , Board board){
        this.user = user;
        this.comment = comment;
        this.board = board;
    }

    public Comment(CommentRequestDto requestDto){
        this.user = requestDto.getUser();
        this.comment = requestDto.getComment();
        this.board = requestDto.getBoard();
    }
}
