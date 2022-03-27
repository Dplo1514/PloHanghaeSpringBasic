package com.plo.ploboardproject.domain;

import com.plo.ploboardproject.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

//    1) @OneToMany : board의 입장에서 comment를 여러개 가지기 때문에 One - Many 관계이다.
//     mappedBy : comment가 board와 어떤 관계를 가지고 있는지를 표시하는 속성이다.
//     commnet는 @JoinColumn을 사용해서 board와의 관계를 표시하지만,
//     board는 mappedBy를 이용해서 comment와의 관계를 표시한다.
//     mappedBy에 들어가는 것은 Board에서의 comments 객체 변수이다.
    @OneToMany(mappedBy = "comment", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Comment> comments;


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
