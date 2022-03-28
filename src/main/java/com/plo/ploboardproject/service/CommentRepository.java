package com.plo.ploboardproject.service;

import com.plo.ploboardproject.domain.Board;
import com.plo.ploboardproject.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment , Long> {
    List<Comment> findAllByOrderByModifiedAtDesc(); //변경사항을 추적해 오름차순 정렬

}
