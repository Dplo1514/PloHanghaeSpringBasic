package com.plo.ploboardproject.service;

import com.plo.ploboardproject.domain.Board;
import com.plo.ploboardproject.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment , Long> {
    List<Comment> findByBoard_idOrderByModifiedAtDesc(@Param(value = "boardId") long boardId);
    List<Comment> findByUser(long userindex);
}
