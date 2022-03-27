package com.plo.ploboardproject.service;
import com.plo.ploboardproject.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByModifiedAtDesc(); //변경사항을 추적해 오름차순 정렬
}
