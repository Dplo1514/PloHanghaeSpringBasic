package com.plo.ploboardproject.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment; // 댓글 내용

    //post_id가져오기
    @ManyToOne
    @JoinColumn(name = "posts_id")
    private BoardWrite boardWrite;

    //작성자 이름 가져오기
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
