package com.plo.ploboardproject.dto;


import com.plo.ploboardproject.domain.Board;
import com.plo.ploboardproject.domain.Comment;
import lombok.*;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDto {
    private Long id;
    private String comment;
    private String user;
    private Board board;



    public Comment toEntity() {
        Comment comments = Comment.builder()
                .board(board)
                .id(id)
                .comment(comment)
                .user(user).
                build();
        return comments;
    }
}
